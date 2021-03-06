package com.hainiu.cat.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * create by biji.zhao on 2020/11/28
 * id, title, address, price, area, detail_url, norms, first_image, house_type
 */
@TableName("house")
public class House implements Serializable {

    private static final long serialVersionUID = -1350675190355449864L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("title")
    private String title;

    @TableField("address")
    private String address;

    @TableField("price")
    private Integer price;

    @TableField("area")
    private Double area;

    @TableField("detail_url")
    private String detailUrl;

    @TableField("norms")
    private String norms;

    @TableField("first_image")
    private String firstImage;

    @TableField(value = "house_type")
    private Integer houseType;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "province")
    private String province;

    @TableField(value = "district")
    private String district;

    @TableField(value = "min_build_area")
    private Double minBuildArea;

    @TableField(value = "max_build_area")
    private Double maxBuildArea;

    private String tags;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Double getMinBuildArea() {
        return minBuildArea;
    }

    public void setMinBuildArea(Double minBuildArea) {
        this.minBuildArea = minBuildArea;
    }

    public Double getMaxBuildArea() {
        return maxBuildArea;
    }

    public void setMaxBuildArea(Double maxBuildArea) {
        this.maxBuildArea = maxBuildArea;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
