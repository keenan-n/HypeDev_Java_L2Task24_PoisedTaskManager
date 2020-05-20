public class ProjectPersonal {

    // Customer:
    public static class NewCustomer {
        //Customer Attributes:
        static String customerName;
        static String customerTelNum;
        static String customerEmail;
        static String customerPhyAdd;

        /**
         * @param customerName   - Customer Name
         * @param customerTelNum - Customer Telephone Number
         * @param customerEmail  - Customer Email
         * @param customerPhyAdd - Customer Physical Address
         */
        public NewCustomer(String customerName,
                           String customerTelNum,
                           String customerEmail,
                           String customerPhyAdd) {
            NewCustomer.customerName = customerName;
            NewCustomer.customerTelNum = customerTelNum;
            NewCustomer.customerEmail = customerEmail;
            NewCustomer.customerPhyAdd = customerPhyAdd;
        }

        /**
         * Create String to Display Object.
         *
         * @return - Object toString
         */
        public String toString() {
            String output =
                    "______________________________________________________________________\n";
            output += "Customer Details:";
            output += "\n______________________________________________________________________";
            output += "\nCustomer Name:.........................  " + customerName;
            output += "\nCustomer Telephone Number:.............  " + customerTelNum;
            output += "\nCustomer Email Address:................  " + customerEmail;
            output += "\nCustomer Physical Address:.............  " + customerPhyAdd;
            output += "\n______________________________________________________________________";

            return output;
        }

        /**
         * Customer Details are sent here to convert data to String for writing
         * to ProjectFile.txt
         *
         * @param customerDetails - String Customer Details for Project.
         * @return - Data for ProjectFile.txt
         */
        public static String toTxtFile(String customerDetails) {
            customerDetails += ("Customer Details, ");
            customerDetails += (customerName + ", ");
            customerDetails += (customerTelNum + ", ");
            customerDetails += (customerEmail + ", ");
            customerDetails += (customerPhyAdd);

            return customerDetails;
        }

    }

    // Architect:
    public static class NewArchitect {
        //Architect Attributes:
        static String archName;
        static String archTelNum;
        static String archEmail;
        static String archPhyAdd;


        /**
         * @param archName   - Architect Name
         * @param archTelNum - Architect Telephone Number
         * @param archEmail  - Architect Email
         * @param archPhyAdd - Architect Physical Address
         */
        public NewArchitect(String archName,
                            String archTelNum,
                            String archEmail,
                            String archPhyAdd) {
            NewArchitect.archName = archName;
            NewArchitect.archTelNum = archTelNum;
            NewArchitect.archEmail = archEmail;
            NewArchitect.archPhyAdd = archPhyAdd;
        }

        /**
         * Create String to Display Object.
         * @return - Object toString.
         */
        public String toString() {
            String output =
                    "______________________________________________________________________\n";
            output += "Project Architect:";
            output += "\n______________________________________________________________________";
            output += "\nArchitect Name:........................  " + archName;
            output += "\nArchitect Telephone Number:............  " + archTelNum;
            output += "\nArchitect Email Address:...............  " + archEmail;
            output += "\nArchitect Physical Address:............  " + archPhyAdd;
            output += "\n______________________________________________________________________";

            return output;
        }

        /**
         * Architect Details are sent here to convert data to String for writing
         * to ProjectFile.txt
         * @param architectDetails - String Architect Details - for Project
         * @return - Data for ProjectFile.txt
         */
        public static String toTxtFile(String architectDetails) {

            // Architect Details: List:
            architectDetails += ("Architect Details, ");
            architectDetails += (archName + ", ");
            architectDetails += (archTelNum + ", ");
            architectDetails += (archEmail + ", ");
            architectDetails += (archPhyAdd);

            return architectDetails;

        }

    }


    // Building Contractor:
    public static class NewBuildContractor {
        // Builders Attributes:
        static String buildName;
        static String buildTelNum;
        static String buildEmail;
        static String buildPhyAdd;

        /**
         * @param buildName - Builder Name
         * @param buildTelNum - Builder Telephone Number
         * @param buildEmail - Builder Email
         * @param buildPhyAdd - Builder Physical Address
         */
        public NewBuildContractor(String buildName,
                                  String buildTelNum,
                                  String buildEmail,
                                  String buildPhyAdd) {
            NewBuildContractor.buildName = buildName;
            NewBuildContractor.buildTelNum = buildTelNum;
            NewBuildContractor.buildEmail = buildEmail;
            NewBuildContractor.buildPhyAdd = buildPhyAdd;
        }

        /**
         * Create String to Display Object.
         * @return - Object toString.
         */
        public String toString() {
            String output =
                    "______________________________________________________________________\n";
            output += "Builder Details:";
            output += "\n______________________________________________________________________";
            output += "\nBuilder Name:..........................  " + buildName;
            output += "\nBuilder Telephone Number:..............  " + buildTelNum;
            output += "\nBuilder Email Address:.................  " + buildEmail;
            output += "\nBuilder Physical Address:..............  " + buildPhyAdd;
            output += "\n______________________________________________________________________";

            return output;
        }


        /**
         * Builder Details are sent here to convert data to String for writing
         * to ProjectFile.txt
         * @param builderDetails - String Builder Details - for Project
         * @return - Data for ProjectFile.txt
         */
        public static String toTxtFile(String builderDetails) {
            builderDetails += ("Builder Details, ");
            builderDetails += (buildName + ", ");
            builderDetails += (buildTelNum + ", ");
            builderDetails += (buildEmail + ", ");
            builderDetails += (buildPhyAdd);

            return builderDetails;
        }

    }

}
