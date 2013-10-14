class GenerateCSVFile {

    private static final int NUM_USERS = 100000
    private static final int NUM_ITEMS = 20
    private static final int NUM_PRODUCT = 100
    private static final Random random = new Random()


    public static void main(String[] args) {
        new File('generated_input.csv').withWriter { out ->

            int userid
            NUM_USERS.times{
                userid = it+1
                NUM_ITEMS.times {
                    out.writeLine("${userid},${random.nextInt(NUM_PRODUCT)},5")
                }
            }
        }
    }
}
