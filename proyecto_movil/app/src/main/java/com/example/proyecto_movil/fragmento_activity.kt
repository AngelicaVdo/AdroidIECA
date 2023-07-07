import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.proyecto_movil.R

class fragmento_activity : Fragment() {
    companion object{
        private  const val ARG_PAGE_NUMBER ="page_number"
        fun newInstance(pageNumber: Int): fragmento_activity{
            val fragment = fragmento_activity()
            val args = Bundle()
            args.putInt(ARG_PAGE_NUMBER, pageNumber)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar la lógica del fragmento aquí

        val textView : TextView
        val pageNumber = arguments?.getInt(ARG_PAGE_NUMBER)
        textView = view.findViewById(R.id.textView)
        textView.text = "Pagina $pageNumber"
    }
}
