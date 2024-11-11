package network.tcp.autoclosable;

public class ResourceCloseMainV3 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws Exception {
        ResourceV1 resourceV1 = null;
        ResourceV1 resourceV2 = null;

        try {
            resourceV1 = new ResourceV1("resource1");
            resourceV2 = new ResourceV1("resource2");


            resourceV1.call();
            resourceV2.callEx();

        } catch (CallException e) {
            System.out.println("ex : " + e);
            throw e;
        } finally {
            System.out.println("자원 정리");
            if (resourceV2 != null ) {

                try {
                    resourceV2.closeEx();
                }catch (CloseException e) {
                    // close()에서 발생한 예외는 버림
                    System.out.println("close ex: " + e);
                }

            }
            if(resourceV1 != null) {
                try {
                    resourceV1.closeEx();
                } catch (CloseException e) {
                    // close()에서 발생한 예외는 버림
                    System.out.println("close: " + e);
                }
            }
        }

    }
}