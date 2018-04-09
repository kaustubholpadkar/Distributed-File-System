
public class IpMainClass {

	public static void main(String[] args) {
		try {
			
			NameNode node = new NameNode(1024, 2);
			node.updateIPs();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
