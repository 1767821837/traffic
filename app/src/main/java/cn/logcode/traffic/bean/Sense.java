package cn.logcode.traffic.bean;

public class Sense {

    @Override
    public String toString() {
        return "Sense{" +
                "data=" + data +
                '}';
    }

    /**
     * data : {"getsense":{"temperature":29,"humidity":8,"co2":358,"LightIntensity":543,"pm2_5":166,"address":3,"address1":4,"address2":2}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * getsense : {"temperature":29,"humidity":8,"co2":358,"LightIntensity":543,"pm2_5":166,"address":3,"address1":4,"address2":2}
         */

        private GetsenseBean getsense;

        public GetsenseBean getGetsense() {
            return getsense;
        }

        public void setGetsense(GetsenseBean getsense) {
            this.getsense = getsense;
        }

        public static class GetsenseBean {
            @Override
            public String toString() {
                return "GetsenseBean{" +
                        "temperature=" + temperature +
                        ", humidity=" + humidity +
                        ", co2=" + co2 +
                        ", LightIntensity=" + LightIntensity +
                        ", pm2_5=" + pm2_5 +
                        ", address=" + address +
                        ", address1=" + address1 +
                        ", address2=" + address2 +
                        '}';
            }

            public GetsenseBean(int temperature, int humidity, int co2, int lightIntensity, int pm2_5, int address) {
                this.temperature = temperature;
                this.humidity = humidity;
                this.co2 = co2;
                LightIntensity = lightIntensity;
                this.pm2_5 = pm2_5;
                this.address = address;

            }

            /**
             * temperature : 29
             * humidity : 8
             * co2 : 358
             * LightIntensity : 543
             * pm2_5 : 166
             * address : 3
             * address1 : 4
             * address2 : 2
             */

            private int temperature;
            private int humidity;
            private int co2;
            private int LightIntensity;
            private int pm2_5;
            private int address;
            private int address1;
            private int address2;

            public int getTemperature() {
                return temperature;
            }

            public void setTemperature(int temperature) {
                this.temperature = temperature;
            }

            public int getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public int getCo2() {
                return co2;
            }

            public void setCo2(int co2) {
                this.co2 = co2;
            }

            public int getLightIntensity() {
                return LightIntensity;
            }

            public void setLightIntensity(int LightIntensity) {
                this.LightIntensity = LightIntensity;
            }

            public int getPm2_5() {
                return pm2_5;
            }

            public void setPm2_5(int pm2_5) {
                this.pm2_5 = pm2_5;
            }

            public int getAddress() {
                return address;
            }

            public void setAddress(int address) {
                this.address = address;
            }

            public int getAddress1() {
                return address1;
            }

            public void setAddress1(int address1) {
                this.address1 = address1;
            }

            public int getAddress2() {
                return address2;
            }

            public void setAddress2(int address2) {
                this.address2 = address2;
            }
        }
    }
}
