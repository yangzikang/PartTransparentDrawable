package com.example.yangzikang.demo;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class HoleDrawable extends Drawable {

    private Paint srcPaint;
    private Path srcPath = new Path();

    private Drawable background;


    public HoleDrawable(Drawable background) {
        this.background = background;
        srcPath.addOval(0, 0, 0, 0, Path.Direction.CW); //初始化了个大小
        srcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        srcPaint.setColor(0xffffffff);
    }

    /**
     * 设置内部透明的部分
     *
     * @param srcPath
     */
    public void setSrcPath(Path srcPath) {
        this.srcPath = srcPath;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        background.setBounds(getBounds());
        if (srcPath == null || srcPath.isEmpty()) {
            background.draw(canvas);
        } else {
            //将绘制操作保存到新的图层，因为图像合成是很昂贵的操作，将用到硬件加速，这里将图像合成的处理放到离屏缓存中进行
            int saveCount = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), srcPaint, Canvas.ALL_SAVE_FLAG);

            //dst 绘制目标图
            background.draw(canvas);

            //设置混合模式
            srcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            //src 绘制源图
            canvas.drawPath(srcPath, srcPaint);
            //清除混合模式
            srcPaint.setXfermode(null);
            //还原画布
            canvas.restoreToCount(saveCount);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        background.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        background.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return background.getOpacity();
    }
}
