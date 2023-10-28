package com.atguigu.spzx.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author atguigu
 * @since 2023-10-27
 */
@Getter
@Setter
@TableName("order_info")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员_id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 使用的优惠券
     */
    @TableField("coupon_id")
    private Long couponId;

    /**
     * 订单总额
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 优惠券
     */
    @TableField("coupon_amount")
    private BigDecimal couponAmount;

    /**
     * 原价金额
     */
    @TableField("original_total_amount")
    private BigDecimal originalTotalAmount;

    /**
     * 运费
     */
    @TableField("feight_fee")
    private BigDecimal feightFee;

    /**
     * 支付方式【1->微信 2->支付宝】
     */
    @TableField("pay_type")
    private Integer payType;

    /**
     * 订单状态【0->待付款；1->待发货；2->已发货；3->待用户收货，已完成；-1->已取消】
     */
    @TableField("order_status")
    private Integer orderStatus;

    /**
     * 收货人姓名
     */
    @TableField("receiver_name")
    private String receiverName;

    /**
     * 收货人电话
     */
    @TableField("receiver_phone")
    private String receiverPhone;

    /**
     * 收货人地址标签
     */
    @TableField("receiver_tag_name")
    private String receiverTagName;

    /**
     * 省份/直辖市
     */
    @TableField("receiver_province")
    private Long receiverProvince;

    /**
     * 城市
     */
    @TableField("receiver_city")
    private Long receiverCity;

    /**
     * 区
     */
    @TableField("receiver_district")
    private Long receiverDistrict;

    /**
     * 详细地址
     */
    @TableField("receiver_address")
    private String receiverAddress;

    /**
     * 支付时间
     */
    @TableField("payment_time")
    private LocalDateTime paymentTime;

    /**
     * 发货时间
     */
    @TableField("delivery_time")
    private LocalDateTime deliveryTime;

    /**
     * 确认收货时间
     */
    @TableField("receive_time")
    private LocalDateTime receiveTime;

    /**
     * 订单备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 取消订单时间
     */
    @TableField("cancel_time")
    private LocalDateTime cancelTime;

    /**
     * 取消订单原因
     */
    @TableField("cancel_reason")
    private String cancelReason;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除标记（0:未删除 1:已删除）
     */
    @TableField("is_deleted")
    private Integer isDeleted;


}
