package es.upm.etsiinf.sos;

public class Client{
    public static void main(java.lang.String args[]){
        try{
            Axis2UserGuideServiceStub stub =
                new Axis2UserGuideServiceStub
                ("http://localhost:8080/axis2/services/Axis2UserGuideService");

            doInOnly(stub);
            twoWayOneParameterEcho(stub);
            noParameters(stub);
            multipleParameters(stub);

        } catch(Exception e){
            e.printStackTrace();
            System.out.println("\n\n\n");
        }
    }

    /* fire and forget */
    public static void doInOnly(Axis2UserGuideServiceStub stub){
        try{
            DoInOnlyRequestDocument req =
                DoInOnlyRequestDocument.Factory.newInstance();
            DoInOnlyRequestDocument.DoInOnlyRequest data =
                req.addNewDoInOnlyRequest();

            data.setMessageString("fire and forget it!");

            stub.doInOnly(req);
            System.out.println("done");
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("\n\n\n");
        }
    }

    /* two way call/receive */
    public static void twoWayOneParameterEcho(Axis2UserGuideServiceStub stub){
        try{
            TwoWayOneParameterEchoRequestDocument req =
                TwoWayOneParameterEchoRequestDocument.Factory.newInstance();
            TwoWayOneParameterEchoRequestDocument.TwoWayOneParameterEchoRequest data =
                req.addNewTwoWayOneParameterEchoRequest();

            data.setEchoString("echo! ... echo!");

            TwoWayOneParameterEchoResponseDocument res =
                stub.twoWayOneParameterEcho(req);

            System.out.println(res.getTwoWayOneParameterEchoResponse().getEchoString());
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("\n\n\n");
        }
    }

    /* No parameters */
    public static void noParameters(Axis2UserGuideServiceStub stub){
        try{
            NoParametersRequestDocument req =
                NoParametersRequestDocument.Factory.newInstance();
            NoParametersRequestDocument.NoParametersRequest data =
                req.addNewNoParametersRequest();

            System.out.println(stub.noParameters(req));
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("\n\n\n");
        }
    }

    /* multiple parameters */
    public static void multipleParameters(Axis2UserGuideServiceStub stub){
        try{
            MultipleParametersAddItemRequestDocument req =
                MultipleParametersAddItemRequestDocument.Factory.newInstance();
            MultipleParametersAddItemRequestDocument.
                MultipleParametersAddItemRequest data =
                req.addNewMultipleParametersAddItemRequest();

            data.setPrice((float)1.99);
            data.setItemId((int)23872983);
            data.setDescription("Must have for cooking");
            data.setItemName("flour");

            MultipleParametersAddItemResponseDocument res =
                stub.multipleParametersAddItem(req);
            MultipleParametersAddItemResponseDocument.
                MultipleParametersAddItemResponse dataRes =
                res.getMultipleParametersAddItemResponse();

            System.out.println(dataRes.getSuccessfulAdd());
            System.out.println(dataRes.getItemId());
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("\n\n\n");
        }
    }
}