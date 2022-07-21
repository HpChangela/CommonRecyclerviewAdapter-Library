### Hi There 👋 👋

If you are looking :eyes: for simple way to create recyclerview adapter and manage row views? Well in this case this library will surly help you. Take a look how it works and which problems it can solved or fixed. 

      
## CommonRecyclerviewAdapter

#### Benefits and Feature of this library
- Reduce number of classes for recycler view adapter by creating one common adapter for all recycler view.
- Imlementation and creation of adapter is smooth and easy.
- The way of managing data and view for recycler view is now simple.
- Provide utility methods for basic operation like add data, update list and remove data etc. 

 **Note : _To use this library you need to use data binding for xml._** 

Steps:
#### 1) Add maven into your project level gradle file.
```
maven { url 'https://jitpack.io' }
```

Note:
If you using Android Studio "Chipmunk" or higher version of it then as per new changes in gradle file you have to add this line into settings.gradle file.

Inside `setting.gradle`  file add  `maven { url 'https://jitpack.io' }`  as below example

```
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } //  This is our line
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } //  This is our line
    }
}
```


#### 2) Now Add following library/dependency into moudule/app level `build.gradle`  file.
```
implementation 'com.github.HpChangela:CommonRecyclerviewAdapter-Library:1.0.4'
```

#### 3) Create your local adapter variable
   >      ex:lateinit var commonAdapter
   - Next, Inherite CommonRecyclerViewAdapter class. 
   >      ex: lateinit var commonAdapter: CommonRecyclerViewAdapter<[YOUR_DATA_CLASS] ,[VIEW_BINDING]>
   - Now, Add same data class type as 1st parameter here I am using "Product" class as example.
   >      ex: lateinit var commonAdapter: CommonRecyclerViewAdapter<Product ,[VIEW_BINDING]>
   - Finally, Add viewbinding reference of your "row item layout" here I am adding "RowAppointmentBinding". 
   >      ex: lateinit var commonAdapter: CommonRecyclerViewAdapter<Product ,RowAppointmentBinding>
   - Done 
   >    lateinit var commonAdapter: CommonRecyclerViewAdapter<Product, RowAppointmentBinding>
   
   
#### 4)Initialize commonAdapter variable as below.

 ```
   commonAdapter = object : CommonRecyclerViewAdapter<Product, RowAppointmentBinding>(context = this,layoutId = R.layout.row_appointment)
 ```         
            
  - Pass context and layoutId in constructor. 
    >      CommonRecyclerViewAdapter<Product,RowAppointmentBinding>(context = this,layoutId = R.layout.row_appointment) 
          
          
 #### 5) Inherite and Override  getViewData() function/method.
    
 ```
   commonAdapter = object : CommonRecyclerViewAdapter<Product, RowAppointmentBinding>(context = this,layoutId = R.layout.row_appointment)
   {
        override fun getViewData(itemView: RowAppointmentBinding, position: Int, itemList: MutableList<Product>) 
       {
            itemView // this parameter will give you access of view are used in row file. ex TextView and Buttoun etc.
            position // it will give you position of view
            itemList // return you data list in MutableList form. 
       }
   }
  ```
      

#### 6) Use views and data.
 ```
      override fun getViewData(itemView: RowAppointmentBinding, position: Int, itemList: MutableList<Product>) 
     {
             val data= itemList[position] // local data instance 
             
                itemView.apply {
                    tvText.text = data.name  // row layout views

                    tvText.setOnClickListener {
                        Toast.makeText(this@MainActivity,"${data.id}",Toast.LENGTH_SHORT).show()
                    }
                }
     }
  ```

#### 7) Attach adapter instance to recycler view. 
```
    binding.rvMain.apply {
            layoutManager= LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
            this.adapter = commonAdapter
     }
```     
     
#### 8)At Last add data into recycler view by addData() method.  
  ```
       //Add data into recycler view
        val list = mutableListOf<Product>()
        list.add(Product(id = "1", "Hp Laptop"))
        list.add(Product(id = "2", "Dell Laptop"))
        list.add(Product(id = "3", "Apple Laptop"))

        commonAdapter.addData(list)   
 ```
 
 :tada: :tada:                                                                                                     
 
##### Same way you can create multipal recyclerview by only one class CommonRecyclerviewAdapter, It will reduce your efforts and number of files multipal adapters requierd. Also, seting data into views and manging call backs are easy with this no need to do that in seperate files.       
