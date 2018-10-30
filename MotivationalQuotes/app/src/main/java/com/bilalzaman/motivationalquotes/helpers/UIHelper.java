package com.bilalzaman.motivationalquotes.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.bilalzaman.motivationalquotes.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class UIHelper {

    public static void showLongToastInCenter(Context ctx, int messageId) {
        Toast toast = Toast.makeText(ctx, messageId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static int getWidthInPixel(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }


    public static void showLongToastInCenter(Context ctx, String message) {
        //message = Strings.nullToEmpty( message );
        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    public static void hideSoftKeyboard(Context context, EditText editText) {

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

    }

    public static void Alert(final Context context, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(
                context).create();

        // Setting Dialog Title
        alertDialog.setTitle(context.getResources().getString(R.string.app_name));

        // Setting Dialog Message
        alertDialog.setMessage(msg);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public static void callAlert(final Activity context, final String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);


        // Setting Dialog Message
        alertDialog.setMessage(msg);

        // Setting OK Button
        alertDialog.setPositiveButton("Call", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                call(context, msg);

            }
        });

        alertDialog.setNegativeButton("Cancel", null);
        alertDialog.create();

        // Showing Alert Message
        alertDialog.show();
    }


    public static String getImagesUrl() {
        return "http://servicedesk.pk/woo_rides/";

    }

    public static void showSoftKeyboard(Context context, EditText editText) {

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.SHOW_IMPLICIT, 0);

    }

    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        if (view != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    public static Rect locateView(View v) {
        int[] loc_int = new int[2];
        if (v == null)
            return null;
        try {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException npe) {
            // Happens when the view doesn't exist on screen anymore.
            return null;
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + v.getWidth();
        location.bottom = location.top + v.getHeight();
        return location;
    }

    public static void textMarquee(TextView txtView) {
        // Use this to marquee Textview inside listview

        txtView.setEllipsize(TruncateAt.END);
        // Enable to Start Scroll

        // txtView.setMarqueeRepeatLimit(-1);
        // txtView.setHorizontallyScrolling(true);
        // txtView.setSelected(true);
    }


    public static void animateRise(final ViewGroup mLayout) {

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(250);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        animation.setDuration(500);
        set.addAnimation(animation);

        animation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLayout.setVisibility(View.INVISIBLE);
            }
        });

        mLayout.startAnimation(set);

    }

    public static void animateFall(ViewGroup mLayout) {

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(250);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(500);
        set.addAnimation(animation);

        mLayout.startAnimation(set);

    }


    public static void animateLayoutSlideDown(ViewGroup mLayout) {

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(250);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(150);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(
                set, 0.25f);
        mLayout.setLayoutAnimation(controller);

    }

    public static void animateLayoutSlideToRight(ViewGroup mLayout) {

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(750);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        animation.setDuration(750);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(
                set, 0.25f);
        mLayout.setLayoutAnimation(controller);

    }

    public static void animateLayoutSlideFromRight(ViewGroup mLayout) {

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(750);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        animation.setDuration(750);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(
                set, 0.25f);
        mLayout.setLayoutAnimation(controller);

    }

    public static void animateLayoutSlideToLeft(ViewGroup mLayout) {

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(750);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        animation.setDuration(750);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(
                set, 0.25f);
        mLayout.setLayoutAnimation(controller);

    }


    public static void animateFromRight(ViewGroup mLayout) {

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(250);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(500);
        set.addAnimation(animation);

        mLayout.startAnimation(set);

    }


    public static void animateToRight(ViewGroup mLayout) {

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(250);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(500);
        set.addAnimation(animation);

        mLayout.startAnimation(set);

    }


    public static Bitmap getThumbnail(Context context, Uri uri, int size) throws IOException {
        int rotate = checkImageForRotation(uri.getPath());
        InputStream input = context.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > size) ? (originalSize / size) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        input.close();
        return rotatedBitmap;
    }

    public static int checkImageForRotation(String imagePath) {
        ExifInterface ei;
        int rotate = 0;
        try {
            ei = new ExifInterface(imagePath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                    rotate = 0;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotate;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) return 1;
        else {
            return k;
        }
    }

    public static Drawable convertUriToDrawable(Context context, Uri imageUri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
            return Drawable.createFromStream(inputStream, imageUri.toString());
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static File saveImage(Bitmap _bitmap, String fileName, Context context) {
        File file = new File(fileName);
        ValidateFolderExist(file.getParent());
        FileOutputStream outStream = null;
        try {
            file.createNewFile();
            outStream = new FileOutputStream(file);
            _bitmap.compress(Bitmap.CompressFormat.PNG, 90, outStream);
            outStream.flush();
            outStream.close();
            //addImageToGallery(file.getAbsolutePath(), context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void ValidateFolderExist(String folderPath) {
        File dir = new File(folderPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


    public static String loadJSONFromAsset(Context context) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            InputStreamReader reader = new InputStreamReader(context
                    .getAssets().open("jsondata.json"));
            br = new BufferedReader(reader);
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public static void smsShare(final Activity context, String phNumber, String messageBody) {
        String sendTo = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            sendTo = "smsto: " + phNumber;
        } else {
            sendTo = "sms:" + phNumber;
        }

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse(sendTo));
        sendIntent.putExtra("sms_body", messageBody);

        try {
            context.startActivity(sendIntent);
        } catch (Exception e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, "SMS service is not available", Toast.LENGTH_LONG).show();
                }
            });
        }

    }


    public static void sendEmail(String[] to, String[] cc, String body, Activity ctx, String subject) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.parse("mailto:"));

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        if (to != null)
            emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        ctx.startActivity(Intent.createChooser(emailIntent, "Send email via..."));
    }

    public static boolean isEmptyOrNull(String string) {
        if (string == null)
            return true;

        return (string.trim().length() <= 0);
    }

    public static String captilize(String name) {
        char[] chars = name.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == ' ') { // You can add other chars here
                found = false;
            }
        }

        return String.valueOf(chars);
    }

    public static String captilizeAfterDot(String name) {
        char[] chars = name.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == ' ') { // You can add other chars here
                found = false;
            }
        }

        return String.valueOf(chars);
    }

    public static String capitalizeScentance(String name) {
      /*  final StringBuilder result = new StringBuilder(name.length());
        String[] words = name.split("\\s");
        for(int i=0,l=words.length;i<l;++i) {
            if (i > 0) result.append(" ");
            result.append(Character.toUpperCase(words[i].charAt(0)))
                    .append(words[i].substring(1));
        }
        StringBuilder sb = new StringBuilder(name);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.setCharAt(name.indexOf(" ") + 1, Character.toUpperCase(sb.charAt(name.indexOf(" ") + 1)));
        sb.setCharAt(name.indexOf(".") + 1, Character.toUpperCase(sb.charAt(name.indexOf(".") + 1)));*/
        char[] chars = name.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == ' ') { // You can add other chars here
                found = false;
            }
        }

        return String.valueOf(chars);
    }

    public static String getFormattedAddress(Address address) {
        String addressName = "";
               /* for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    addressName += address.getAddressLine(i) + " ";
                }*/
        String location = "";
        String city = address.getLocality();
        //String distric = addresses.get(0).getAdminArea();
        String country = "";
        if (address.getAdminArea() != null && address.getAdminArea().length() >= 2) {
            String[] arr = address.getAdminArea().split(" ");
            if (arr != null) {
                if (arr.length > 1) {
                    country = String.valueOf(arr[0].charAt(0)) + String.valueOf(arr[1].charAt(0));
                    country = country.toUpperCase();
                } else {
                    country = address.getAdminArea().substring(0, 2);
                    country = country.toUpperCase();
                }
            } else {
                country = country.toUpperCase();
            }

        } else
            country = address.getAdminArea();

        String postalCode = address.getPostalCode();
        //  sb.insert(location.indexOf("CA") + 2, ", ");

        //location.replaceAll(" ",", ");
                /*if(address!=null){
                    location += address;
				}*/

        if (city != null && city.length() > 0) {
            location += city;
        }
        if (country != null && country.length() > 0) {
            if (city != null && city.length() > 0)
                location += ", " + country;
            else
                location += country;
        }
        if (postalCode != null && postalCode.length() > 0) {
            if (country != null && country.length() > 0)
                location += ", " + postalCode;
            else
                location += postalCode;
        }
        if (location.length() == 0)
            location = "N/A";
        return location;

    }


    public static boolean isNotNull(String str) {
        return str != null && str.length() > 0;

    }

    public static double faircalculation(float mils) {
        double price;
        // float meters=GMSGeometryLength(routePath);
        //float mils= Float.parseFloat(DistanceCalculator.getDistanceBetweenLocation(startLocation,endLocation));

        if (mils >= 3.00) {
            price = 35.00;
        } else if (mils >= 0.34 && mils <= 0.66) {
            price = 7.50;
        } else if (mils >= 0.67 && mils <= 0.99) {
            price = 15.00;
        } else if (mils >= 1.00 && mils <= 1.99) {
            price = 20.00;
        } else if (mils >= 2.00 && mils <= 2.99) {
            price = 30.00;
        } else {
            price = 5.00;
        }
        return price;

    }

    public static void call(Activity activity, String number) {

        Uri call = Uri.parse("tel:" + number);
        Intent surf = new Intent(Intent.ACTION_DIAL, call);
        activity.startActivity(surf);
    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static void openActivity(Activity activity, Class<?> calledActivity) {
        Intent myIntent = new Intent(activity, calledActivity);
        activity.startActivity(myIntent);
    }

    public static void openAndClearActivity(Activity activity, Class<?> calledActivity) {
        Intent myIntent = new Intent(activity, calledActivity);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(myIntent);
    }

    //    public static  void showSnackBar(String msg, Context context, View view){
//        Snackbar snackbar = Snackbar
//                .make(view, msg, Snackbar.LENGTH_LONG);
//                /*.setAction("RETRY", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                    }
//                });*/
//
//
//        snackbar.setActionTextColor(context.getResources().getColor(R.color.colorWhite));
//        View sbView = snackbar.getView();
//        sbView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(context.getResources().getColor(R.color.white));
//        snackbar.show();
//    }
//    public static void initSingltons(Context context) {
//        PreferenceHelper.getInstance(context).init(context);
//        WebServices.getInstance().init(context);
//        //SpecialSharedPrefHelper.getInstance().init(context);
//    }
//
//    public static boolean isNetworkAvailable(Context context) {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }

    public static int calculateTabWidth(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x / 3;
    }
}