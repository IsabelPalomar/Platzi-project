package com.platzi.platzigram;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.platzi.platzigram.utils.Constants;

/**
 * Created by lucy on 28/09/16.
 */

public class PlatzigramApplication extends Application{

    StorageReference storageReference;
    DatabaseReference postReference;

    Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl(Constants.FIREBASE_STORAGE_URL);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);

        postReference = firebaseDatabase.getReference(Constants.FIREBASE_DATABASE_LOCATION_POST);

        this.context = this;

    }

    public StorageReference getStorageReference(){
        return  storageReference;
    }

    public DatabaseReference getPostReference(){
        return postReference;
    }
    public Context getContext(){
        return context;
    }
}
