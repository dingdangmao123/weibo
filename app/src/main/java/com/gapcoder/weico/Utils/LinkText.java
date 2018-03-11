package com.gapcoder.weico.Utils;

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
 * Created by suxiaohui on 2018/3/4.
 */

public class LinkText {


    final  String title = "(#[^#]+#)";
    final String  weico="(@[^\\s@]+)";
    final String url="(https?://(/|[0-9a-zA-Z]|\\.|%)+)";

     Pattern T = Pattern.compile(title);
     Pattern W = Pattern.compile(weico);
    Pattern U = Pattern.compile(url);
    Context context;

    public LinkText(Context context) {
        this.context = context;
    }

    public  CharSequence parse(String text){
        SpannableString span= new SpannableString(text);


        Matcher m=T.matcher(text);
        while(m.find()) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
            span.setSpan(colorSpan, m.start(), m.end(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            span.setSpan(new LinkSpan(m.group()), m.start(), m.end(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        m=W.matcher(text);
        while(m.find()) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
            span.setSpan(colorSpan, m.start(), m.end(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            span.setSpan(new LinkSpan(m.group()), m.start(), m.end(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        m=U.matcher(text);
        while(m.find()) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
            span.setSpan(colorSpan, m.start(), m.end(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            span.setSpan(new LinkSpan(m.group()), m.start(), m.end(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        return span;
    }

    class LinkSpan extends ClickableSpan {

        String content;

        public LinkSpan(String content) {
            this.content = content;
        }

        @Override
        public void onClick(View v) {


            Log.i("tag",content);
            Intent i=null;
            if(content.startsWith("@")) {
                i = new Intent(context, User.class);
                i.putExtra("name",content.replaceAll("@|#",""));
            }else if(content.startsWith("#")) {
                i = new Intent(context, Title.class);
                i.putExtra("title",content.replaceAll("@|#",""));
            }else if(content.startsWith("http")) {
                i = new Intent(context, Web.class);
                i.putExtra("url",content.replaceAll("@|#",""));
            }else
                return ;
            context.startActivity(i);
            }
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }
        }
    }
