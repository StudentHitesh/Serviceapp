package com.example.prajapatifamily.selectimagefromgallery;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.hp.navigationapk.R;
import com.example.hp.navigationapk.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class editprofile extends AppCompatActivity implements View.OnClickListener{

    private ImageView profile_img;

    private ImageView camera;

    private String userChoosenTask;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try {

            setContentView(R.layout.activity_editprofile);

            setUI();

            setUITEXT();

        }catch (Exception e){

            e.printStackTrace();

        }

    }



    private void setUITEXT() {





    }



    private void setUI() {



        profile_img = (ImageView)findViewById(R.id.imageView8);

        camera = (ImageView)findViewById(R.id.imageView9);

        camera.setOnClickListener(this);





    }



    @Override

    public void onClick(View view) {



        switch (view.getId()){



            case R.id.imageView9:



                selectImage();



                break;



        }







    }



    @Override

    protected void onResume() {

        super.onResume();

    }





    @Override

    protected void onPause() {

        super.onPause();

    }





    @Override

    protected void onDestroy() {

        super.onDestroy();

    }







    private void selectImage() {



        final CharSequence[] items = { "Take Photo", "Choose from Library",

                "Cancel" };



        AlertDialog.Builder builder = new AlertDialog.Builder(com.example.prajapatifamily.selectimagefromgallery.editprofile.this);

        builder.setTitle("Add Photo!");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                boolean result=Utility.checkPermission(com.example.prajapatifamily.selectimagefromgallery.editprofile.this);



                if (items[item].equals("Take Photo")) {

                    userChoosenTask ="Take Photo";

                    if(result)

                        cameraIntent();



                } else if (items[item].equals("Choose from Library")) {


                    userChoosenTask ="Choose from Library";

                    if(result)

                        galleryIntent();



                } else if (items[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();





    }



    private void galleryIntent() {



        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);//

        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);



    }



    private void cameraIntent() {



        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, REQUEST_CAMERA);



    }





    @Override

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if(userChoosenTask.equals("Take Photo"))

                        cameraIntent();

                    else if(userChoosenTask.equals("Choose from Library"))

                        galleryIntent();

                } else {

                    //code for deny

                }

                break;

        }

    }



    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);



        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == SELECT_FILE)

                onSelectFromGalleryResult(data);

            else if (requestCode == REQUEST_CAMERA)

                onCaptureImageResult(data);

        }

    }



    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);



        File destination = new File(Environment.getExternalStorageDirectory(),

                System.currentTimeMillis() + ".jpg");



        FileOutputStream fo;

        try {

            destination.createNewFile();

            fo = new FileOutputStream(destination);

            fo.write(bytes.toByteArray());

            fo.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }



        profile_img.setImageBitmap(thumbnail);


    }



    @SuppressWarnings("deprecation")

    private void onSelectFromGalleryResult(Intent data) {



        Bitmap bm=null;

        if (data != null) {

            try {

                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());

            } catch (IOException e) {

                e.printStackTrace();

            }

        }



        profile_img.setImageBitmap(bm);

    }



}
