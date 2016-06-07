package com.rtbtz.other;

/**
 *  Special implementation of circular buffer or ring buffer for storing messages
 * @author Petr
 */
public class RingBuffer {
    private String[] array;
    private int length = 0;
    private int count = 0;
    private int iterator = 0;
    
    public RingBuffer(int size){
        array = new String[size];
    }
    
    public RingBuffer(RingBuffer rb, int size){
        array = new String[size];
        rb.reset();
        for(int i = 0, k = 0; i < rb.getLength() && k < array.length; i++, k++){
            push(rb.getNext());
        }
    }
    
    public void push(String text){
        array[count] = text;
        
        if(count + 1 < array.length){
            count++;
            if(length < array.length) {
                length++;
            }
        }else{
            count = 0;
            length = array.length;
        }
    }
    
    
    public void reset(){
        if(array[array.length - 1] == null){
            iterator = 0;
        } else {
            iterator = count;
        }
    }
    
    public String getNext(){
        String result = array[iterator];
        iterator = nextIndex(iterator);
        return result;
    }
    
    public int getLength(){
        return length;
    }
    
    private int nextIndex(int index){
        if(index + 1 < array.length){
            index++;
        }else{
            index = 0;
        }
        
        return index;
    }
}