package com.example.fastfashion

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.example.fastfashion.fragments.DatePickerDialogFragment
import com.example.fastfashion.model.FashionItem
import com.example.fastfashion.model.FashionItemCreate
import com.example.fastfashion.network.FashionInteractor
import com.livinglifetechway.quickpermissions.annotations.OnPermissionsDenied
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import com.livinglifetechway.quickpermissions.util.QuickPermissionsRequest
import kotlinx.android.synthetic.main.activity_upload.*
import java.io.File
import java.io.IOException

class UploadActivity : AppCompatActivity(), DatePickerDialogFragment.DateListener {

    val REQUEST_IMAGE_CAPTURE = 1
    var mCurrentPhotoPath: String=""
    private val fashionInteractor = FashionInteractor()

    private var userId=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title=getString(R.string.title_upload)
        setContentView(R.layout.activity_upload)
        userId=intent.getIntExtra("id", 0)
        ivPicture.setOnClickListener { dispatchTakePictureIntent() }
        btnSave.setOnClickListener { uploadItem() }
        etDate.setOnClickListener { showDatePickerDialog() }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerDialogFragment()
        val fm= this@UploadActivity.supportFragmentManager
        datePicker.show(fm,"TAG")
    }

    override fun onDateSelected(date: String) {
        etDate.setText(date)
    }


    private fun uploadItem(){
        if(valid()){
           /* val item = FashionItem(0, tvCategory.text.toString(), tvDesc.text.toString(),
                tvStyle.text.toString(), tvDate.text.toString(),mCurrentPhotoPath)*/
            val item = FashionItemCreate( etCategory.text.toString(), etDesc.text.toString(),
                etStyle.text.toString(), etDate.text.toString(),mCurrentPhotoPath, userId)
            fashionInteractor.addFashionItem(item, this::onUploadSuccess, this::onUploadError)
        }
        else{
            Toast.makeText(applicationContext, getString(R.string.err_upload), Toast.LENGTH_LONG).show()
        }
    }

    private fun onUploadSuccess(item: FashionItem?){
        if(item==null){
            onUploadError(Exception("item is null"))
        }
        else{
            Toast.makeText(applicationContext, getString(R.string.upload_succ), Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun onUploadError(e:Throwable){
        e.printStackTrace()
        Toast.makeText(applicationContext,getString(R.string.upload_unsucc), Toast.LENGTH_LONG).show()
    }

    private fun valid(): Boolean{
        var retVal=true
        if(mCurrentPhotoPath=="") {
            retVal=false
        }
        if(etCategory.text.toString()==""){
            retVal=false
            etStyle.error=getString(R.string.cat_empty)
        }
        if(etDate.text.toString()=="") {
            retVal=false
            etDate.error=getString(R.string.date_empty)
        }
        if(etDesc.text.toString()==""){
            retVal=false
            etDesc.error=getString(R.string.detail_empty)
        }
        if (etStyle.text.toString() == ""){
            retVal=false
            etStyle.error=getString(R.string.style_empty)
        }
        return retVal
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageView.setImageBitmap(imageBitmap);
            galleryAddPic()
        }
    }



    @Throws(IOException::class)
    private fun createImageFile(): File {
        val storageDir = Environment.getExternalStorageDirectory()
        val image = File.createTempFile(
            "example", /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )
        mCurrentPhotoPath = image.absolutePath
        return image
    }

    @WithPermissions(
        permissions = [Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE]
    )
    private fun dispatchTakePictureIntent() {
        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile))
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun galleryAddPic() {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(mCurrentPhotoPath)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        ivPicture.setImageBitmap(MediaStore.Images.Media.getBitmap(contentResolver, contentUri))
        this.sendBroadcast(mediaScanIntent)
    }

    @OnPermissionsDenied
    fun onDenied(arg: QuickPermissionsRequest) {
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.permissionMissSnackbar), Snackbar.LENGTH_LONG).show()

    }
}
