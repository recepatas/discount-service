package com.recepatas;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;

public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue() throws ParseException {
        String[] args = {};
        App.main(args);
        assertTrue( true );
    }
}
