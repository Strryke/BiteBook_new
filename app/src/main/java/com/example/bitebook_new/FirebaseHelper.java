package com.example.bitebook_new;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class FirebaseHelper {
    private String uid;
    private DatabaseReference ref;
    FirebaseUser user;


    public static String generateRandomString() {
        long time = System.currentTimeMillis();
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random(time);

        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }

        return sb.toString();
    }


    public static void createEntry(Context context, Entry entry, Bitmap bitmap) {

        String uid = getCurrentUser(context);

        if (uid != null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://bitebook-380210-default-rtdb.asia-southeast1.firebasedatabase.app/");
            DatabaseReference myRef = database.getReference(uid + "/entries");
            DatabaseReference newRef = myRef.push();


            // saving photos
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            byte[] pictureData = baos.toByteArray();
//
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//            // Create a storage reference from our app
//            StorageReference storageRef = storage.getReference();
//            // Create a child reference
//            // imagesRef now points to "images"
//            StorageReference imagesRef = storageRef.child("images");
//
//            UploadTask uploadTask = imagesRef.putBytes(pictureData);
//            uploadTask.addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle unsuccessful uploads
//                    System.out.println("asdfasdfasdf u failed noob");
//                }
//            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//                    // ...
//                }
//            });

            newRef.setValue(entry);
        }
    }

    public static String getCurrentUser(Context context) {
        String uid = null;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        GoogleSignInAccount gAccount = GoogleSignIn.getLastSignedInAccount(context);

        if (gAccount != null) {
            uid = gAccount.getId();
        } else if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                uid = profile.getUid();
            }
        } else {
            System.out.println("no user found");
        }

        return uid;
    }

}
