package com.wjs.produce;

import com.wjs.model.util.BeanUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wenjs
 */
public class CloneTest {

    public static void main(String[] args) {
        CloneTest test = new CloneTest();

        CloneModel cloneModel = test.getCloneModel();
        CloneModel cloneModelNew = test.createCloneModel();

        BeanUtils.copyProperties(cloneModel, cloneModelNew);

        System.out.println(cloneModelNew.toString());

        CloneA cloneA = test.createCloneA();
        cloneA.doClone();

    }
    CloneA createCloneA() {
        CloneA cloneA = new CloneA();
        cloneA.setAge(12);
        cloneA.setName("wenjs");
        cloneA.setAddress(new Address("Addr"));
        return cloneA;
    }

    CloneModel createCloneModel() {
        return new CloneModel();
    }

    CloneModel getCloneModel() {
        CloneModel cloneModel = new CloneModel();
        cloneModel.setAge(18);
        cloneModel.setName("wenjs");
        cloneModel.setAddress(new Address("Addr"));
        return cloneModel;
    }

    @Setter
    @Getter
    @ToString
    public class CloneModel {
        private String name;
        private int age;
        private Address address;
    }

    @Setter
    @Getter
    @ToString
    public class Address {
        private String adr;

        public Address(String adr) {
            this.adr = adr;
        }

    }

    @ToString
    public class CloneA implements Cloneable{
        private String name;
        private Integer age;
        private Address address;

        public Address getAddress() {
            return address;
        }

        public CloneA setAddress(Address address) {
            this.address = address;
            return this;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public CloneA setName(String name) {
            this.name = name;
            return this;
        }

        public CloneA setAge(int age) {
            this.age = age;
            return this;
        }

        @Override
        protected CloneA clone()  {
            CloneA sheep = null;
            try {
                sheep = (CloneA)super.clone();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return sheep;
        }

        private void print(){
            System.out.println(
                    String.format("%s->%s:%s->%s",this.hashCode(),
                            this.getName().hashCode(),
                            this.getAge().hashCode(),
                            this.getAddress().hashCode()));
        }


        public void doClone(){
            this.clone().print();
            this.clone().print();
            this.clone().setName("01").setAge(17).print();
            this.clone().setName("02").setAge(16).print();
            this.clone().setName("03").setAge(15).print();
            this.clone().setName("04").setAge(14).print();
            this.clone().setName("05").setAge(13).print();
        }
    }
}
