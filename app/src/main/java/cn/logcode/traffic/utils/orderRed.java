package cn.logcode.traffic.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.logcode.traffic.bean.Beanred;
import cn.logcode.traffic.bean.BusBeans;

public class orderRed {
    public static List<Beanred.DataBean> OrderList(List<Beanred.DataBean> dataBeans, int falg) {
        Collections.sort(dataBeans, new Comparator<Beanred.DataBean>() {
            @Override
            public int compare(Beanred.DataBean dataBean, Beanred.DataBean t1) {
                int mun = 0;
                switch (falg) {
                    case 0:
                        mun = dataBean.getId() - t1.getId();
                        break;
                    case 1:
                        mun = -(dataBean.getId() - t1.getId());
                        break;
                    case 2:
                        mun = dataBean.getRed() - t1.getRed();
                        break;
                    case 3:
                        mun = -(dataBean.getRed() - t1.getRed());
                        break;
                    case 4:
                        mun = dataBean.getGreen() - t1.getGreen();
                        break;
                    case 5:
                        mun = -(dataBean.getGreen() - t1.getGreen());
                        break;
                    case 6:
                        mun = dataBean.getYellow() - t1.getYellow();
                        break;
                    case 7:
                        mun = -(dataBean.getYellow() - t1.getYellow());
                        break;
                }
                return mun;
            }
        });
        return dataBeans;
    }

    public static BusBeans orderBus(BusBeans busBeans) {
//        busBeans
        Log.i("*************", "orderBus: "+busBeans);
        List<BusBeans.DataBean> list = busBeans.getData();
        for (int i = 0; i < list.size(); i++) {
            List<BusBeans.DataBean.BusBean> bus = list.get(i).getBus();
            Collections.sort(bus, new Comparator<BusBeans.DataBean.BusBean>() {
                @Override
                public int compare(BusBeans.DataBean.BusBean busBean, BusBeans.DataBean.BusBean t1) {
                    return busBean.getDistance()-t1.getDistance();
                }
            });
            busBeans.getData().get(i).setBus(bus);
        }

        Log.i("*************", "orderBus: "+busBeans);
        return busBeans;
    }





}


