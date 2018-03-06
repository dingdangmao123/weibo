package com.gapcoder.weico.Comment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import com.gapcoder.weico.Title.Title;
import com.gapcoder.weico.User.User;
import com.gapcoder.weico.Web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by suxiaohui on 2018/3/6.
 */

public class Reply {

    Context context;

    public Reply(Context context) {
        this.context = context;
    }

    public CharSequence parse(String text, int uid, String name) {
        String pre = "回复";
        SpannableString span = new SpannableString(pre + name + ": " + text);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        span.setSpan(colorSpan, pre.length(), pre.length() + name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span.setSpan(new LinkSpan(uid), pre.length(), pre.length() + name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return span;
    }

    class LinkSpan extends ClickableSpan {


        int uid;

        public LinkSpan(int uid) {
            this.uid = uid;
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, User.class);
            i.putExtra("uid", uid);
            Log.i("uid",String.valueOf(uid));
            context.startActivity(i);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }
    }
}