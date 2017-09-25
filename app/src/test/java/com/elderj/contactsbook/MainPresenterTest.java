package com.elderj.contactsbook;

import org.junit.Before;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MainPresenterTest {

    private MainActivityView view;

    @Before
    public void setUp() {
        view = mock(MainActivityView.class);
    }

}