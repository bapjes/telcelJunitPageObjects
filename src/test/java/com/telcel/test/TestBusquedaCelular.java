package com.telcel.test;

import com.telcel.utils.Celular;
import org.junit.Test;

public class TestBusquedaCelular  extends ParentTest{

    @Test
    public void testVerificarCelular() throws Exception {
        Celular cellphone;

        home.goToPage(url);
        home.isTelcelHomePage();
        home.selectSmartPhone();
        modal.isModalPage();
        modal.selectState(state);
        phones.listofPhones();
        cellphone = phones.selectPhone(phone);
        phoneinfo.compareCellphoneselected(cellphone);

    }
}
