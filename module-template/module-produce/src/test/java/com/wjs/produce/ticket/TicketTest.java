package com.wjs.produce.ticket;

import org.aspectj.lang.annotation.Before;

/**
 * @ClassName TicketTest
 * @Description: TODO
 * @Author wjs
 * @Date 2020/10/12
 * @Version V1.0
 **/
public class TicketTest {


    public static void main(String[] args) {
        Ticket ticket = new Ticket ();

        ticket.init();

        ticket.print();


    }
}
