package com.sun.component.commonres.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sun.component.commonres.R;
import com.sun.component.commonsdk.utils.FormatUtils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class TimeView extends FrameLayout {

    private Disposable disposable;
    private OnTimeChangeListener onTimeChangeListener;
    private TextView tvHourMinute;
    private TextView tvWeek;
    private TextView tvDate;

    public void setOnTimeChangeListener(OnTimeChangeListener onTimeChangeListener) {
        this.onTimeChangeListener = onTimeChangeListener;
    }

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.time_view, null);
        tvHourMinute = view.findViewById(R.id.tv_hour_minute);
        tvWeek = view.findViewById(R.id.tv_week);
        tvDate = view.findViewById(R.id.tv_date);
        disposable = Flowable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Calendar cal = Calendar.getInstance();
                        String year = FormatUtils.twoNumber(cal.get(Calendar.YEAR));
                        String month = FormatUtils.twoNumber(cal.get(Calendar.MONTH) + 1);
                        String day = FormatUtils.twoNumber(cal.get(Calendar.DATE));
                        String hour;
                        if (cal.get(Calendar.AM_PM) == Calendar.AM)
                            hour = FormatUtils.twoNumber(cal.get(Calendar.HOUR));
                        else
                            hour = FormatUtils.twoNumber(cal.get(Calendar.HOUR) + 12);
                        String minute = FormatUtils.twoNumber(cal.get(Calendar.MINUTE));
                        String second = FormatUtils.twoNumber(cal.get(Calendar.SECOND));

                        tvHourMinute.setText(hour + ":" + minute);
                        switch (cal.get(Calendar.DAY_OF_WEEK)) {
                            case 1:
                                tvWeek.setText("星期天");
                                break;
                            case 2:
                                tvWeek.setText("星期一");
                                break;
                            case 3:
                                tvWeek.setText("星期二");
                                break;
                            case 4:
                                tvWeek.setText("星期三");
                                break;
                            case 5:
                                tvWeek.setText("星期四");
                                break;
                            case 6:
                                tvWeek.setText("星期五");
                                break;
                            case 7:
                                tvWeek.setText("星期六");
                                break;
                        }
                        tvDate.setText(year + "-" + month + "-" + day);
                        if (onTimeChangeListener != null) {
                            onTimeChangeListener.onTimeChange(hour, minute,second);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable.getMessage());
                    }
                });
        addView(view);
    }

    public void onDestroy() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public void setTextColor(int color) {
        tvHourMinute.setTextColor(color);
        tvWeek.setTextColor(color);
        tvDate.setTextColor(color);
    }

    public interface OnTimeChangeListener {
        void onTimeChange(String hour, String minute,String second);
    }
}
