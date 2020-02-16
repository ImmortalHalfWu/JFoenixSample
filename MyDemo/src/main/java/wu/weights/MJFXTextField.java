package wu.weights;

import com.jfoenix.controls.JFXTextField;

public class MJFXTextField extends JFXTextField {

    private FocusedChangeListener focusedChangeListener;

    MJFXTextField() {
        super();
        focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                boolean validate = validate();
                if (focusedChangeListener != null) {
                    focusedChangeListener.changed(MJFXTextField.this, validate, newValue);
                }
            }
        });
    }

    public MJFXTextField setFocusedChangeListener(FocusedChangeListener focusedChangeListener) {
        this.focusedChangeListener = focusedChangeListener;
        return this;
    }

    interface FocusedChangeListener {
        void changed(MJFXTextField textField, boolean validate, boolean newValue);
    }



}
