package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fiyyanp on 10/12/2016.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "process",
        "id_user",
        "asal",
        "tujuan",
        "jarak",
        "lat_asal",
        "long_asal",
        "lat_tujuan",
        "long_tujuan",
        "id_user_driver",
        "lat_driver",
        "long_driver",
        "harga",
        "order_id",
        "drivers"
})
public class RequestBody {
    @JsonProperty("process")
    public String process;
    @JsonProperty("id_user")
    public String idUser;
    @JsonProperty("asal")
    public String asal;
    @JsonProperty("tujuan")
    public String tujuan;
    @JsonProperty("jarak")
    public String jarak;
    @JsonProperty("lat_asal")
    public String latAsal;
    @JsonProperty("long_asal")
    public String longAsal;
    @JsonProperty("lat_tujuan")
    public String latTujuan;
    @JsonProperty("long_tujuan")
    public String longTujuan;
    @JsonProperty("id_user_driver")
    public String idDriver;
    @JsonProperty("lat_driver")
    public String latDriver;
    @JsonProperty("long_driver")
    public String longDriver;
    @JsonProperty("harga")
    public String harga;
    @JsonProperty("order_id")
    public String orderId;
    @JsonProperty("drivers")
    private List<String> drivers = new ArrayList<String>();
    @JsonProperty("estimasi")
    public String estimasi;
    @JsonProperty("estimasi_waktu")
    public String estimasiWaktu;

    /**
     *
     * @return
     * The process
     */
    @JsonProperty("process")
    public String getProcess() {
        return process;
    }

    /**
     *
     * @param process
     * The process
     */
    @JsonProperty("process")
    public void setProcess(String process) {
        this.process = process;
    }

    /**
     *
     * @return
     * The idUser
     */
    @JsonProperty("id_user")
    public String getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     * The id_user
     */
    @JsonProperty("id_user")
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     *
     * @return
     * The asal
     */
    @JsonProperty("asal")
    public String getAsal() {
        return asal;
    }

    /**
     *
     * @param asal
     * The asal
     */
    @JsonProperty("asal")
    public void setAsal(String asal) {
        this.asal = asal;
    }

    /**
     *
     * @return
     * The tujuan
     */
    @JsonProperty("tujuan")
    public String getTujuan() {
        return tujuan;
    }

    /**
     *
     * @param tujuan
     * The tujuan
     */
    @JsonProperty("tujuan")
    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    /**
     *
     * @return
     * The jarak
     */
    @JsonProperty("jarak")
    public String getJarak() {
        return jarak;
    }

    /**
     *
     * @param jarak
     * The jarak
     */
    @JsonProperty("jarak")
    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    /**
     *
     * @return
     * The latAsal
     */
    @JsonProperty("lat_asal")
    public String getLatAsal() {
        return latAsal;
    }

    /**
     *
     * @param latAsal
     * The lat_asal
     */
    @JsonProperty("lat_asal")
    public void setLatAsal(String latAsal) {
        this.latAsal = latAsal;
    }

    /**
     *
     * @return
     * The longAsal
     */
    @JsonProperty("long_asal")
    public String getLongAsal() {
        return longAsal;
    }

    /**
     *
     * @param longAsal
     * The long_asal
     */
    @JsonProperty("long_asal")
    public void setLongAsal(String longAsal) {
        this.longAsal = longAsal;
    }

    /**
     *
     * @return
     * The latTujuan
     */
    @JsonProperty("lat_tujuan")
    public String getLatTujuan() {
        return latTujuan;
    }

    /**
     *
     * @param latTujuan
     * The lat_tujuan
     */
    @JsonProperty("lat_tujuan")
    public void setLatTujuan(String latTujuan) {
        this.latTujuan = latTujuan;
    }

    /**
     *
     * @return
     * The longTujuan
     */
    @JsonProperty("long_tujuan")
    public String getLongTujuan() {
        return longTujuan;
    }

    /**
     *
     * @param longTujuan
     * The long_tujuan
     */
    @JsonProperty("long_tujuan")
    public void setLongTujuan(String longTujuan) {
        this.longTujuan = longTujuan;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getLatDriver() {
        return latDriver;
    }

    public void setLatDriver(String latDriver) {
        this.latDriver = latDriver;
    }

    public String getLongDriver() {
        return longDriver;
    }

    public void setLongDriver(String longDriver) {
        this.longDriver = longDriver;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<String> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<String> drivers) {
        this.drivers = drivers;
    }

    public String getEstimasi() {
        return estimasi;
    }

    public void setEstimasi(String estimasi) {
        this.estimasi = estimasi;
    }

    public String getEstimasiWaktu() {
        return estimasiWaktu;
    }

    public void setEstimasiWaktu(String estimasiWaktu) {
        this.estimasiWaktu = estimasiWaktu;
    }
}
