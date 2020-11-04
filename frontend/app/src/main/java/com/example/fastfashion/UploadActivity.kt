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
import com.livinglifetechway.quickpermissions.annotations.OnPermissionsDenied
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import com.livinglifetechway.quickpermissions.util.QuickPermissionsRequest
import kotlinx.android.synthetic.main.activity_upload.*
import java.io.File
import java.io.IOException

class UploadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        ivPicture.setOnClickListener { dispatchTakePictureIntent() }
    }

    val REQUEST_IMAGE_CAPTURE = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageView.setImageBitmap(imageBitmap);
            galleryAddPic()
        }
    }

    var mCurrentPhotoPath: String=""

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
