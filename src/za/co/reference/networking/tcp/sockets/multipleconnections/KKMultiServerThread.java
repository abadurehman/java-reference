package za.co.reference.networking.tcp.sockets.multipleconnections;

/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import za.co.reference.networking.tcp.sockets.singleconnection.KnockKnockProtocol;

public class KKMultiServerThread extends Thread {
    private Socket socket = null;

    public KKMultiServerThread(Socket socket) {
	super("KKMultiServerThread");
	this.socket = socket;
    }

    public void run() {

	try {
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(
				    new InputStreamReader(
				    socket.getInputStream()));

	    String inputLine, outputLine;
	    KnockKnockProtocol kkp = new KnockKnockProtocol();
	    outputLine = kkp.processInput(null);
	    out.println(outputLine);

	    while ((inputLine = in.readLine()) != null) {
		outputLine = kkp.processInput(inputLine);
		out.println(outputLine);
		if (outputLine.equals("Bye"))
		    break;
	    }
	    out.close();
	    in.close();
	    socket.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}