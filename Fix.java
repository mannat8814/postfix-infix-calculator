import java.util.*;
public class Fix
{
    public static void main(String[]args)
    {
        HayStack<Character> poststk =new HayStack();
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter an expression with single-digit positive integers, using any of the following operators: +-*/().");
        System.out.println("Don't include any spaces.");
        String infix = reader.next();
        String p = postfix(infix);
        System.out.println("Here is the postfix: "+p);
        System.out.println("Here is the answer: "+ evaluatePost(p));
    }
    public static int prioritize(char c)
    {
        int x=0;
        switch(c)
        {
            case '*': 
            case '/':
            x=2; break;
            case '+':
            case'-':
            x=1; break;
            //default: x=0;break;
            
            /*case'*'://if want to add more operations, just add cases above, dont have to redefine x
            case'/':
            x++;
            case'-':
            case'+':
            x++;break;*/
        }
        return x;
    }
    
    public static String postfix(String i)
    {
        char c; 
        String post="";
        char prev=' ';
        HayStack<Character> poststk =new HayStack();
        for(int x=0;x<i.length();x++)
        {
            c = i.charAt(x);
            if(Character.isDigit(c))
            post+=c;
             else if(c=='(') 
            poststk.push(c);//whatver comes after '(' will always be another ( or an int
            else if(c==')')
            {
                while(prev!='(')
                {
                    post+=poststk.pop();
                    prev=poststk.peek();
                }
                poststk.pop();//remove left parenth
            }
            else 
            {
                while(prioritize(prev)>=prioritize(c)&& !poststk.isEmpty())
                {
                    post+=poststk.pop();
                    if(!poststk.isEmpty())prev=poststk.peek();
                 }
                poststk.push(c);
            }
            if(!poststk.isEmpty())prev=poststk.peek();//for input like (2+3)
        }
        while(!poststk.isEmpty())
        post+=poststk.pop();
        return post;
    }
    public static HayStack<Integer> evaluatePost(String post)
    {
        HayStack<Integer> i = new HayStack();
        char c;
        int left;
        int right;
        for(int x=0; x<post.length();x++)
        {
            c=post.charAt(x);
            if(Character.isDigit(c))
                i.push(Integer.parseInt(String.valueOf(c)));
            else
            {
                right=i.pop();
                left=i.pop();
                switch(c)
                {
                    case '+':
                    i.push(left+right);break;
                    case '-':
                    i.push(left-right);break;
                    case '*':
                    i.push(left*right);break;
                    case '/':
                    i.push(left/right);break;
                }
            }
            
        }
        return i;
    }
}
