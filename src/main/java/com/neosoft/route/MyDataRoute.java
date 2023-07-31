package com.neosoft.route;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;


@Component
public class MyDataRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

       // from("file:C:\\Users\\User\\Desktop\\source").to("file:C:\\Users\\User\\Desktop\\destination");

        from("file:C:\\Users\\User\\Desktop\\source")
                .process(
                        (ex)-> {

                            //read input message given by source.
                            Message input = ex.getIn();
                            //read body as string from input message.
                            String data  =  input.getBody(String.class);
                            //operation.

                            StringTokenizer str = new   StringTokenizer(data, ",");
                            String eid = str.nextToken();
                            String ename = str.nextToken();
                            String esal = str.nextToken();

                            //output message.
                            String dataModified = "{eid:"+eid +",ename"+ename + ",esal"+esal+"}";

                            //read output message reference.
                            Message output = ex.getMessage();

                            //set data to output.
                            output.setBody(dataModified);



                        }
                )
              /*  .process(new Processor() {

                    public void process(Exchange exchange) throws Exception {

                        //read input message given by source.
                        Message input = exchange.getIn();
                        //read body as string from input message.
                     String data  =  input.getBody(String.class);
                     //operation.

                        StringTokenizer str = new   StringTokenizer(data, ",");
                        String eid = str.nextToken();
                        String ename = str.nextToken();
                        String esal = str.nextToken();

                        //output message.
                        String dataModified = "{eid:"+eid +",ename"+ename + ",esal"+esal+"}";

                        //read output message reference.
                        Message output = exchange.getMessage();

                        //set data to output.
                        output.setBody(dataModified);

                    }
                })*/
                .to("file:C:\\Users\\User\\Desktop\\destination?fileName=emp.json");



    }
}
