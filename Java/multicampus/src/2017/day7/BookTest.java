package day7;
class Book{
	String title;
	String author;
	int price;
	Book(){
		this("Java의 정석", "남궁성", 30000);
	}
	Book(String title,String author,int price){
		this.title=title;
		this.author=author;
		this.price=price;
		
	}
	String getBookInfo(){
		return title+" : "+author+" : "+price;
	}
}
public class BookTest {

	public static void main(String[] args) {
		Book book1=new Book();
		Book book2=new Book("신경끄기의 기술","마크맨슨",13500);
		Book book3=new Book("혜민스님의 따듯한 응원","혜민스님",12600);
		Book book4=new Book("언어의 온도","이기주",12420);
		Book book5=new Book("트렌드코리아2018","김난도",16200);
		System.out.println(book1.getBookInfo());
		System.out.println(book2.getBookInfo());
		System.out.println(book3.getBookInfo());
		System.out.println(book4.getBookInfo());
		System.out.println(book5.getBookInfo());	
	}

}
