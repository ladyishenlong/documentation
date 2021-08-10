#Android加入购物车赛贝尔曲线动画效果

``` java
public class Anim {


    private final long durationTime = 500;//动画持续时间


    /**
     * 添加商品到购物车的动画效果
     *
     * @param goodsImg       进行于移动的商品图片就是列表里商品图片
     * @param relativeLayout 页面父布局
     * @param shoppingCarImg 购物车图片
     */
    public void addGoodsToCart(Context context, ImageView goodsImg, RelativeLayout relativeLayout, ImageView shoppingCarImg, String productImageUrl) {


        //这里用superTextView承载一个圆形的图片
        final SuperTextView goods = new SuperTextView(context);
        goods.setUrlImage(productImageUrl);
        goods.setCorner(50);//设置圆角
        goods.setStrokeColor(Color.RED);//边框颜色
        goods.setStrokeWidth(4);//边框宽度


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(68, 68);
        relativeLayout.addView(goods, params);

        //父布局起始坐标
        int[] parentLocation = new int[2];
        relativeLayout.getLocationInWindow(parentLocation);

        //得到商品的图片坐标
        int startLoc[] = new int[2];
        goodsImg.getLocationInWindow(startLoc);

        //购物车图片坐标
        int endLoc[] = new int[2];
        shoppingCarImg.getLocationInWindow(endLoc);


        //开始掉落的商品的起始点：
        //简单来说就是该商品图片的中心点
        float startX = startLoc[0] - parentLocation[0];
        float startY = startLoc[1] - parentLocation[1];

        // 商品掉落后的终点坐标：购物车起始点-父布局起始点
        float toX = endLoc[0] - parentLocation[0];
        float toY = endLoc[1] - parentLocation[1];

        //绘制贝塞尔曲线
        Path path = new Path();
        //路径移动到起始点
        path.moveTo(startX, startY);
        // 使用二阶贝塞尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大
        path.quadTo((startX + toX) / 2, startY, toX, toY);

        //用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，如果是true，path会形成一个闭环
        PathMeasure pathMeasure = new PathMeasure(path, false);

        // 属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.setDuration(durationTime);//图片持续时间


        // 贝塞尔曲线中间过程点坐标
        float[] currentPosition = new float[2];

        // 加速插值器
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到currentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                // currentPosition此时就是中间距离点的坐标值
                pathMeasure.getPosTan(value, currentPosition, null);
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                goods.setTranslationX(currentPosition[0]);
                goods.setTranslationY(currentPosition[1]);
            }
        });


        // 开始执行动画
        valueAnimator.start();
        valueAnimator.setStartDelay(500);//动画延迟执行时间

        // 动画结束后的处理
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                onAddGoodsToCartListener.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //这里可以拿出接口，写购物车的变化
                LogUtils.d("动画效果结束");
                relativeLayout.removeView(goods);//移除移动的图片

                onAddGoodsToCartListener.end();
            }
        });

    }


    public interface OnAddGoodsToCartListener {
        void start();

        void end();
    }

    private OnAddGoodsToCartListener onAddGoodsToCartListener;

    public void setOnAddGoodsToCartListener(OnAddGoodsToCartListener onAddGoodsToCartListener) {
        this.onAddGoodsToCartListener = onAddGoodsToCartListener;
    }
```