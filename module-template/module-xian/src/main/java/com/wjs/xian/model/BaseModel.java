package com.wjs.xian.model;

import lombok.Data;

@Data
public class BaseModel {

    private String name;
    //攻击力
    private Integer atk;
    //防御力
    private Integer def;
    private Integer hp;
    private Integer mp;


    public StringBuilder print(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("name：%s\n",name));
        sb.append(String.format("hp：%s\n",hp));
        sb.append(String.format("mp：%s\n",mp));
        sb.append(String.format("atk：%s\n",atk));
        sb.append(String.format("def：%s\n",def));
        return sb;
    }
}
