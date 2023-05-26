package es.upm.etsiinf.sos;

import es.upm.etsiinf.sos.model.xsd.Username;

public class testing {

    public static void main(String [] args) {

        ETSIINFSocialSkeleton skeleton = new ETSIINFSocialSkeleton();

        Username prueba = new Username();
        prueba.setUsername("Pablo");

        skeleton.addUser(prueba);


    }
    
}
