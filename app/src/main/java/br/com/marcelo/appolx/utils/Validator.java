package br.com.marcelo.appolx.utils;
import android.graphics.Color;
import android.widget.EditText;

/**
 * Created by Marcelo on 09/02/2018.
 */

public class Validator {
    public static String validade(EditText editText) throws IllegalArgumentException {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.requestFocus();
            editText.setHintTextColor(Color.RED);
            throw new IllegalArgumentException("O campo " + editText.getHint() + " é obrigatório!");
        }
        editText.setHintTextColor(Color.LTGRAY);
        return editText.getText().toString().trim();
    }
}
