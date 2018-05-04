#include <iostream>

using namespace std;

class heap
{
    int a[6],i,j,flag;
public:
    heap()
    {
        flag=0;
        for(i=0;i<6;i++)
        {
            cout<<"ënter number";
            cin>>a[i];

        }
    }

    int leftchildindex(int x)
    {
        return ((2*x)+1);
    }
    int rightchildindex(int x)
    {
        return ((2*x)+2);
    }

    void maxheap()
    {
        for(i=0;i<2;i++)
        {
            if(rightchildindex(i)<6)
            {
            if(a[i]>a[leftchildindex(i)] && a[i]>a[rightchildindex(i)])
            {

            }

            else
                {flag=1;break;}
            }
            else
            {
                if(a[i]>a[leftchildindex(i)])
                {

                }

                else
                    {flag=1;break;}
            }
        }

        if(flag==1)
        {
           minheap();// cout<<"array is not a maxheap";
        }
        else if(flag==0)
        {
            cout<<"array is maxheap";
        }
    }
    void minheap()
    {
        flag=0;
        for(i=0;i<2;i++)
        {
            if(rightchildindex(i)<6)
            {
            if(a[i]<a[leftchildindex(i)] && a[i]<a[rightchildindex(i)])
            {

            }
            else
            {
                flag=1;break;
            }
            }
            else
            {
               if(a[i]<a[leftchildindex(i)])
            {

            }
            else
            {
                flag=1;break;
            }
            }
        }
        if(flag==1)
        {
            cout<<"array is neither maxheap nor minheap";
        }
        else if(flag==0)
        {
            cout<<"array is minheap";
        }

    }

};


int main()
{
    heap h1;
    h1.maxheap();
    return 0;
}
