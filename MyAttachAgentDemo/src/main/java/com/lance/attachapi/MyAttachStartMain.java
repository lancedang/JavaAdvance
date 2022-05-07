package com.lance.attachapi;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class MyAttachStartMain {

    public static void main2(String[] args) {
        System.out.println("hi");
    }

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {

        //args[0] = "592";
        VirtualMachine virtualMachine = VirtualMachine.attach("21028");

        try {
            virtualMachine.loadAgent("D:\\idea-project\\JavaAdvance\\MyAttachAgentDemo\\src\\main\\java\\com\\lance\\attachapi\\MyAttachAgentMain.jar");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            virtualMachine.detach();
        }
    }
}
