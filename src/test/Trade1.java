package test;

import model.MyFieldAnnotation;

import java.util.List;

/**
 * Created by is_yo on 2017/1/5.
 */
public class Trade1 {
    @MyFieldAnnotation(primary_key = true, desc = "这个是num", default_value = "")
    int num;
    @MyFieldAnnotation(primary_key = false, desc = "这个是goods_kind", default_value = "0")
    int goods_kind;
    String num_iid;
    String price;
    String pic_path;
    String pic_thumb_path;
    String title;
    String type;
    String discount_fee;
    String order_type;
    String status;
    String status_str;
    String refund_state;
    String shipping_type;
    String post_fee;
    String total_fee;
    String refunded_fee;
    String payment;
    String created;
    String update_time;
    String pay_time;
    String pay_type;
    String consign_time;
    String sign_time;
    String buyer_area;
    int seller_flag;
    String buyer_message;
    List<Orders1> orders;
    String fetch_detail;
    List<String> coupon_details;
    List<String> promotion_details;
    AdjustFee1 adjust_fee;
    List<String> sub_trades;
    String weixin_user_id;
    List<ButtonList1> button_list;
    int feedback_num;
    String trade_memo;
    FansInfo1 fans_info;
    String buy_way_str;
    String pf_buy_way_str;
    int send_num;
    String user_id;
    int kind;
    String relation_type;
    List<String> relations;
    List<String> out_trade_no;
    String group_no;
    int outer_user_id;
    String shop_id;
    String shop_type;
    int points_price;
    int delivery_start_time;
    int delivery_end_time;
    String tuan_no;
    String delivery_time_display;
    String hotel_info;
    String buyer_nick;
    String tid;
    String buyer_type;
    String buyer_id;
    String receiver_city;
    String receiver_district;
    String receiver_name;
    String receiver_state;
    String receiver_address;
    String receiver_zip;
    String receiver_mobile;
    int feedback;
    String outer_tid;
    String service_phone;

}
