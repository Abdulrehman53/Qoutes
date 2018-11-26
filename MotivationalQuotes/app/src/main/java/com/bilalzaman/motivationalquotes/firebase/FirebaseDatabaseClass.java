package com.bilalzaman.motivationalquotes.firebase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bilalzaman.motivationalquotes.models.ExploreModel;
import com.bilalzaman.motivationalquotes.views.activities.ExploreMoreActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by BilalZaman on 23/11/2018.
 */
public class FirebaseDatabaseClass {

    private static FirebaseDatabaseClass mInstance;
    private static Context context;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    public FirebaseDatabaseClass(Context ctx) {
        context = ctx;
    }

    public void loadModelWithDataFirebase(){
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference().child("");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (data != null) {
//                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                        String value = dataSnapshot1.getValue(String.class);
//                        data.add(new ExploreModel(model.getId(), value));
//                        Intent intent = new Intent(context, ExploreMoreActivity.class);
//                        intent.putExtra("name",model.getId());
//                        context.startActivity(intent);
//                        //adapter.notifyDataSetChanged();
//                    }
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Failed to read", databaseError.toString());
            }
        });
    }

    public static synchronized FirebaseDatabaseClass getmInstance(Context context) {

        if (mInstance == null){
            mInstance = new FirebaseDatabaseClass(context);
        }

        return mInstance;
    }

}
