
public class IpMainClass {

	public static void main(String[] args) {
		try {
			
			NameNode node = NameNode.getNode();
			node.updateIPs();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
