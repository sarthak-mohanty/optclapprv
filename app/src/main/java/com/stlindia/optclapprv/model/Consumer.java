package com.stlindia.optclapprv.model;

public class Consumer {
    public String consumer, meter;
    public String phone, date, block, village,status, district,id,aadhar,customer_type,address1,address2,CVC,previous_connections,SDO,binder,load,connections,remarks;
    public String initial_reading,type,TCSN,make,max_digit,capacity,tp1_seal_number,tp2_seal_nummber;

    public Consumer(String consumer, String meter, String phone, String date, String block, String village, String district, String id, String aadhar, String customer_type, String address1, String address2, String CVC, String previous_connections, String SDO, String binder, String load, String connections, String remarks, String initial_reading, String type, String TCSN, String make, String max_digit, String capacity, String tp1_seal_number, String tp2_seal_nummber) {
        this.consumer = consumer;
        this.meter = meter;
        this.phone = phone;
        this.date = date;
        this.block = block;
        this.village = village;
        this.district = district;
        this.id = id;
        this.aadhar = aadhar;
        this.customer_type = customer_type;
        this.address1 = address1;
        this.address2 = address2;
        this.CVC = CVC;
        this.previous_connections = previous_connections;
        this.SDO = SDO;
        this.binder = binder;
        this.load = load;
        this.connections = connections;
        this.remarks = remarks;
        this.initial_reading = initial_reading;
        this.type = type;
        this.TCSN = TCSN;
        this.make = make;
        this.max_digit = max_digit;
        this.capacity = capacity;
        this.tp1_seal_number = tp1_seal_number;
        this.tp2_seal_nummber = tp2_seal_nummber;
        this.status=status;

    }
    public Consumer() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    public String getPrevious_connections() {
        return previous_connections;
    }

    public void setPrevious_connections(String previous_connections) {
        this.previous_connections = previous_connections;
    }

    public String getSDO() {
        return SDO;
    }

    public void setSDO(String SDO) {
        this.SDO = SDO;
    }

    public String getBinder() {
        return binder;
    }

    public void setBinder(String binder) {
        this.binder = binder;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getConnections() {
        return connections;
    }

    public void setConnections(String connections) {
        this.connections = connections;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInitial_reading() {
        return initial_reading;
    }

    public void setInitial_reading(String initial_reading) {
        this.initial_reading = initial_reading;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTCSN() {
        return TCSN;
    }

    public void setTCSN(String TCSN) {
        this.TCSN = TCSN;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMax_digit() {
        return max_digit;
    }

    public void setMax_digit(String max_digit) {
        this.max_digit = max_digit;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getTp1_seal_number() {
        return tp1_seal_number;
    }

    public void setTp1_seal_number(String tp1_seal_number) {
        this.tp1_seal_number = tp1_seal_number;
    }

    public String getTp2_seal_nummber() {
        return tp2_seal_nummber;
    }

    public void setTp2_seal_nummber(String tp2_seal_nummber) {
        this.tp2_seal_nummber = tp2_seal_nummber;
    }
}