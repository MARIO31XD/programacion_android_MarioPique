package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fathzer.soft.javaluator.DoubleEvaluator;



public class MainActivity extends AppCompatActivity {
    private TextView tvRes; // mostrar el resultat
    private StringBuilder expressio = new StringBuilder(); // ex: "33+5+15")

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvRes = findViewById(R.id.tvRes);

        // buttons
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);


        // signos de operaciones (+, -, X, /)
        Button btnPlus = findViewById(R.id.buttonPlus);
        Button btnResta = findViewById(R.id.buttonrestar);
        Button btnmulti = findViewById(R.id.buttonmultiplicar);
        Button btndivision = findViewById(R.id.buttondividir);
        Button btnEquals = findViewById(R.id.buttonEqual);
        Button btnReset = findViewById(R.id.buttonReset);

        // Listeners
        btn1.setOnClickListener(v -> afegirNum("1"));
        btn2.setOnClickListener(v -> afegirNum("2"));
        btn3.setOnClickListener(v -> afegirNum("3"));
        btn4.setOnClickListener(v -> afegirNum("4"));
        btn5.setOnClickListener(v -> afegirNum("5"));
        btn6.setOnClickListener(v -> afegirNum("6"));
        btn7.setOnClickListener(v -> afegirNum("7"));
        btn8.setOnClickListener(v -> afegirNum("8"));
        btn9.setOnClickListener(v -> afegirNum("9"));

        // coloquem cada signe de cada operador
        btnPlus.setOnClickListener(v ->  operacio("+"));
        btnResta.setOnClickListener(v -> operacio("-"));
        btnmulti.setOnClickListener(v -> operacio("*"));
        btndivision.setOnClickListener(v -> operacio("/"));
        btnEquals.setOnClickListener(v -> evaluar());

         // boton para reiniciar la operacion utilitza el metodo resetCalculator
        btnReset.setOnClickListener(v -> resetCalculator());

        actualitzar();
       // restar();
    }

    private void afegirNum(String c) {
        expressio.append(c);
        tvRes.setText(expressio.toString());
        actualitzar();

    }
    /*private void restar() {

    }
    */

    // metode que rep variable de tipus string per diferenciar cada operacio
    private void operacio(String operador) {
        // Permetre '+' només si encara no n'hi ha (evita "33++-5")
        // codi ...

        // comprova si l'expressió no està buida
        if (expressio.length() > 0) {
            // obtenir ultim caracter
            char ultim = expressio.charAt(expressio.length() - 1);
            // condició si el ultim caracter es un d'aquest termina amb un return
            if (ultim == '+' || ultim == '-' || ultim == '*' || ultim == '/') {

                return;
            }
        }


        // li passem la variable que hem indicat abans a l'expressió
        expressio.append(operador);
        tvRes.setText(expressio.toString());




        actualitzar();
    }

    private void evaluar() {
        // https://mvnrepository.com/artifact/com.fathzer/javaluator
        // https://github.com/fathzer/javaluator  3.0.6
        // "(2^3-1)*sin(pi/4)/ln(pi^2)" = 2.1619718020347976

        // evaluem
        DoubleEvaluator evaluator = new DoubleEvaluator();
        Double result = evaluator.evaluate(expressio.toString());

        // mostrem resultat
        // try/catch
        tvRes.setText(result.toString());
        expressio.setLength(0);

    }
    private void resetCalculator() {
        // limpiar operacion
        expressio.setLength(0);
        // limpiar el textview donde se muestra la operacion
        tvRes.setText("");
    }

    private void actualitzar() {
        tvRes.setText(expressio.toString());
    }
}