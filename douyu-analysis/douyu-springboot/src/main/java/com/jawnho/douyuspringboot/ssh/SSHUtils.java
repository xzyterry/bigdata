package com.jawnho.douyuspringboot.ssh;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class SSHUtils {

    private static final String ENCODING = "UTF-8";

    public static Session getJSchSession(DestHost destHost) throws JSchException {
        JSch jsch = new JSch();

        Session session = jsch.getSession(destHost.getUsername(), destHost.getHost(), destHost.getPort());
        session.setPassword(destHost.getPassword());
        session.setConfig("StrictHostKeyChecking", "no");//第一次访问服务器不用输入yes
        session.setTimeout(destHost.getTimeout());
        session.connect();

        return session;
    }

    public static String execCommandByJSch(Session session, String command, String  resultEncoding) throws IOException,JSchException{

        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        channelExec.connect();
        String result = IOUtils.toString(in, resultEncoding);
        channelExec.disconnect();

        return result;

    }

    public static void main(String[] argus) throws JSchException, IOException {
        Scanner sc= new Scanner(System.in);

       /* System.out.print("host:");
        String host = sc.nextLine();

        System.out.print("username:");
        String username = sc.nextLine();

        System.out.print("password:");
        String password = sc.nextLine();*/

        DestHost destHost = new DestHost("192.168.153.128","root","haizhi1234!");

        Session session = getJSchSession(destHost);


        while(sc.hasNext()){
            String s = execCommandByJSch(session, sc.nextLine(), ENCODING);
            System.out.println(s);
        }

    }
}
