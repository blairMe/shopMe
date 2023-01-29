package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentCheckoutBinding
import bfa.blair.shopme.ui.adapters.CartAdapter
import bfa.blair.shopme.utils.Contants
import bfa.blair.shopme.viewmodel.CartViewModel
import com.google.firebase.auth.FirebaseAuth
import com.vickikbt.darajakmp.Daraja
import com.vickikbt.darajakmp.network.models.DarajaPaymentResponse
import com.vickikbt.darajakmp.network.models.DarajaToken
import com.vickikbt.darajakmp.utils.DarajaResult
import com.vickikbt.darajakmp.utils.onFailure
import com.vickikbt.darajakmp.utils.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckoutFragment : Fragment() {

    private var binding : FragmentCheckoutBinding? = null

    private lateinit var firebase : FirebaseAuth

    private val args: CheckoutFragmentArgs by navArgs<CheckoutFragmentArgs>()

    private val cartViewModel : CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebase = FirebaseAuth.getInstance()

        val itemPrice = args.itemPrice
        binding!!.priceTxt.text = "Please confirm that you are making payment of: $itemPrice"
        binding!!.usersEmail.setText(firebase.currentUser!!.email.toString())
            
        binding!!.confirmBtn.setOnClickListener {

            val phoneNumber = binding!!.usersPhone.text.toString()

            val daraja: Daraja = Daraja.Builder()
                .setConsumerSecret(Contants.CONSUMER_SECRET)
                .setConsumerKey(Contants.CONSUMER_KEY)
                .setPassKey(Contants.PASS_KEY)
                //.isProduction() // Optional. Will default to sandbox_mode = true
                .build()


            lifecycleScope.launch {
                try{
                val darajaPaymentResponse: DarajaResult<DarajaPaymentResponse> =
                    daraja.initiateMpesaExpressPayment(
                        businessShortCode = "174379",
                        amount = 1,
                        phoneNumber = phoneNumber,
                        transactionDesc = "ShopMe order",
                        callbackUrl = "https://mydomain.com/path",
                        accountReference = "ShopMe"
                    )

                darajaPaymentResponse
                    .onSuccess { paymentResponse ->
                        // Successfully requested M-Pesa STK request
                        cartViewModel.deleteCart()
                        Toast.makeText(requireContext(), "Product ordered successful, " +
                                "you'll be called when your product is ready for pickup.",
                            Toast.LENGTH_SHORT).show()

                        findNavController().navigate(
                            CheckoutFragmentDirections.actionCheckoutFragmentToCartFragment()
                        )
                    }
                    .onFailure { error ->
                        // Failed to request M-Pesa STK
                        Toast.makeText(requireContext(), "There was an error, please try again.",
                            Toast.LENGTH_SHORT).show()
                    } } catch (ex : Exception) {
                        ex.stackTrace
                    }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}