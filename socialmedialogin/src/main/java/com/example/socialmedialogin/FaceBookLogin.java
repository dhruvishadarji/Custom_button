package com.example.socialmedialogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dhruvisha on 1/23/2019.
 */

public class FaceBookLogin {

    public static CallbackManager callbackManager;

    public static void initilization() {
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public static void FaceBookButton(LoginButton faceBookLogin) {
        faceBookLogin.setReadPermissions("email");
        faceBookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserDetails(loginResult);
                LoginManager.getInstance().logOut();
            }

            @Override
            public void onCancel() {
                Log.d("error", "cancel->");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("error", "error->" + error);
            }
        });

    }

    public static void getUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        /*Intent intent = new Intent(this, Main2Activity.class);
                        intent.putExtra("userProfile", json_object.toString());
                        Log.d("Login attempt", json_object.toString());
                        startActivity(intent);*/

                    }
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }

}

