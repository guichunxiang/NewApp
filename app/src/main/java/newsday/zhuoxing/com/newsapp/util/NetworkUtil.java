package newsday.zhuoxing.com.newsapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import newsday.zhuoxing.com.newsapp.R;


/**
 * Created by lenovo on 2016/7/20.
 */
public class NetworkUtil {
    private LruCache<String,Bitmap> lruCache;
    private RequestQueue requestQueue;
    public NetworkUtil(Context context) {

        requestQueue= Volley.newRequestQueue(context);

    }
    public void getStringResult(String url){

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(request);//将请求队列放入队列中
    }
    /**
     * 设置图片的方法
     * */
    public void setImagePic(final ImageView imageView, final String url){

        //参数1 url 参数2连接成功时的监听
        ImageRequest imageRequest=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                Bitmap newbitmap=createCirclePicture(bitmap);

                    imageView.setImageBitmap(newbitmap);

            }
            //参数3/4大宽度/高度为mach_prent,参数5色彩制式，参数6位连接失败时的
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
       requestQueue.add(imageRequest);
    }
    /**
     * 设置图片及缓存的方法
     * */
    private void setIconImage(final ImageView imageView, final String url){
        imageView.setTag(url);
        ImageLoader imageLoader=new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return lruCache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                lruCache.put(s,bitmap);
            }
        });
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                if (imageView.getTag().toString().equals(url)){
                    imageView.setImageBitmap(imageContainer.getBitmap());
                }

            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                imageView.setImageResource(R.mipmap.cccc);
            }
        });
    }
    /**
     * 圆形图片获取方法
     * */
    public  Bitmap createCirclePicture(Bitmap bitmap){
        Paint paint=new Paint();//画笔
        paint.setAntiAlias(true);//设置抗锯齿


        int min=Math.min(bitmap.getWidth(),bitmap.getHeight());

        //参数1 目标图片宽度，参数2目标图片高度，参数3目标文件颜色制式
        Bitmap target=Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(target);//画布

        //参数1 圆心x轴坐标，参数2圆心y轴坐标，参数3半径，参数4画笔
        canvas.drawCircle(min/2,min/2,min/2,paint);//在画布上画出圆
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//设置画笔模式取交集 SRC_IN
        canvas.drawBitmap(bitmap,min/2-bitmap.getWidth()/2,min/2-bitmap.getHeight()/2,paint);
        return target;
    }
    /**
     * 获取圆角图片的方法
     * */
    public  Bitmap createRoundCornerPicture(Bitmap bitmap){
        Paint paint=new Paint();//画笔
        paint.setAntiAlias(true);//设置抗锯齿


        int min=Math.min(bitmap.getWidth(),bitmap.getHeight());

        //参数1 目标图片宽度，参数2目标图片高度，参数3目标文件颜色制式
        Bitmap target=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(target);//画布

        //参数1 左边的坐标，参数2上边的坐标，参数3右边的坐标，参数4下边的坐标
        RectF rectF=new RectF(0,0,bitmap.getWidth(),bitmap.getHeight());//矩形

        //参数1 矩形，参数2x轴弧度，最大为360，参数3y轴弧度，参数4画笔
        canvas.drawRoundRect(rectF,30,30,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//设置画笔模式取交集 SRC_IN
        canvas.drawBitmap(bitmap,0,0,paint);
        return target;
    }
}
