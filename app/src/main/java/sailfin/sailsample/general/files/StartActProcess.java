package sailfin.sailsample.general.files;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Admin on 29-01-2016.
 */
public class StartActProcess {

    Context mContext;

    public StartActProcess(Context mContext) {
        this.mContext = mContext;
    }

    public void startAct(Class<?> cls) {
        Intent mainIntent = new Intent(mContext, cls);
        mContext.startActivity(mainIntent);
    }

    public void startActWithData(Class<?> cls, Bundle bundle) {
        Intent mainIntent = new Intent(mContext, cls);
        mainIntent.putExtras(bundle);
        mContext.startActivity(mainIntent);
    }

    public void startActForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent mainIntent = new Intent(mContext, cls);
        mainIntent.putExtras(bundle);
        ((Activity) mContext).startActivityForResult(mainIntent, requestCode);
    }

    public void startActForResult(Class<?> cls, int requestCode) {
        Intent mainIntent = new Intent(mContext, cls);
        ((Activity) mContext).startActivityForResult(mainIntent, requestCode);
    }

    public void startActForResult(String cls, int requestCode) {
        Intent mainIntent = new Intent(cls);
        ((Activity) mContext).startActivityForResult(mainIntent, requestCode);
    }

    public void startActForResult(Fragment fragment, Class<?> cls, int requestCode) {
        Intent mainIntent = new Intent(mContext, cls);
        fragment.startActivityForResult(mainIntent, requestCode);
    }

    public void startActForResult(Fragment fragment, Class<?> cls, int requestCode, Bundle bundle) {
        Intent mainIntent = new Intent(mContext, cls);
        mainIntent.putExtras(bundle);
        fragment.startActivityForResult(mainIntent, requestCode);
    }

    public void setOkResult() {
        Intent output = new Intent();
        ((Activity) mContext).setResult(((Activity) mContext).RESULT_OK, output);
    }

    public void setOkResult(Bundle bn) {
        Intent output = new Intent();
        output.putExtras(bn);
        ((Activity) mContext).setResult(((Activity) mContext).RESULT_OK, output);
    }

    public void setOkResult(int resultCode) {
        Intent output = new Intent();
        ((Activity) mContext).setResult(resultCode, output);
    }

    /*public void openURL(String url) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mContext.startActivity(browserIntent);
        } catch (Exception e) {

        }

    }*/


    public boolean openURL(String url) {
        // urls
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mContext.startActivity(browserIntent);
        } catch (Exception e) {

            return false;
        }

        return true;
    }
}
