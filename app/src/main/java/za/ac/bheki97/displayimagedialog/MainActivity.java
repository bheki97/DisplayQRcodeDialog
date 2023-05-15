package za.ac.bheki97.displayimagedialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private ImageView qrCodeImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input_text);
        qrCodeImage = findViewById(R.id.qr_code_image);
    }

    public void onClickGenerate(View view) {
        // Generate QR code from input text
        String text = inputText.getText().toString();
        MultiFormatWriter writer = new MultiFormatWriter();

        final Dialog dialog = new Dialog(MainActivity.this);


        dialog.setContentView(R.layout.dialog_image);

        ImageView imageView = dialog.findViewById(R.id.image_view);


        try {
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);




            imageView.setImageBitmap(bitmap);
            qrCodeImage.setImageBitmap(bitmap);

            Button closeButton = dialog.findViewById(R.id.close_button);
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });



            dialog.setCanceledOnTouchOutside(true);

            // Show the dialog
            dialog.show();

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}