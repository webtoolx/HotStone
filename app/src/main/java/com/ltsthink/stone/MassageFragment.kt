package com.ltsthink.stone


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.TextInputLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.AppCompatEditText
<<<<<<< HEAD
import android.support.v7.widget.PopupMenu
=======
>>>>>>> 0026944e737158b551053a24feabf29d5e80170d
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.github.gcacace.signaturepad.views.SignaturePad
import com.ltsthink.stone.Models.Config
import com.ltsthink.stone.Models.UserInfo
import com.ltsthink.stone.Utils.IOnBackPressed
import kotlinx.android.synthetic.main.fragment_massage.*
import kotlinx.android.synthetic.main.fragment_massage.view.*
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter


class MassageFragment : Fragment() {
    var backButtonCount: Int = 0
//    override fun onBackPressed(): Boolean {
//
//        //action not popBackStack
//        val intent1 = Intent(requireContext(), MainActivity::class.java)
//        startActivity(intent1)
//        return true
//    }


//    override fun  onDetach() {
//        super.onDetach()
//        // PUT YOUR CODE HERE
////        if (backButtonCount >= 1) {
//        val intent1 = Intent(requireContext(), MainActivity::class.java)
//        startActivity(intent1)
//    }
//            val intent = Intent(Intent.ACTION_MAIN)
//            intent.addCategory(Intent.CATEGORY_HOME)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(intent)
//        } else {
//           // Toast.makeText(requireContext(), "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show()
//            backButtonCount++
//        }


<<<<<<< HEAD
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private var mSignaturePad: SignaturePad? = null
    private var sSignaturePad: SignaturePad? = null
    lateinit var txtPhoneLayout: TextInputLayout
    lateinit var txtPhone: AppCompatEditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_massage, container, false)
        txtPhoneLayout = v.findViewById(R.id.textPhoneLayout) as TextInputLayout
        txtPhone = v.findViewById(R.id.textPhone) as AppCompatEditText

        txtPhone.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (txtPhone.text.toString().isEmpty()) {
                txtPhoneLayout.isErrorEnabled = true
                txtPhoneLayout.error = "Required Field!"
            } else {
                txtPhoneLayout.isErrorEnabled = false
                existUser(v.textPhone.text.toString())
            }
        }

        val nm: AppCompatEditText = v.findViewById(R.id.textName) as AppCompatEditText
        val ph: AppCompatEditText = v.findViewById(R.id.textPhone) as AppCompatEditText
        val em: AppCompatEditText = v.findViewById(R.id.textEmail) as AppCompatEditText

        nm.setText(UserInfo.client_name, TextView.BufferType.EDITABLE)
        ph.setText(UserInfo.client_phone, TextView.BufferType.EDITABLE)
        em.setText(UserInfo.client_email, TextView.BufferType.EDITABLE)

        nm.isEnabled = false
        ph.isEnabled = false
        em.isEnabled = false

        var url = Config.SITE_URL + "massages"
        var url2 = Config.SITE_URL + "user/check-health"

        val jsonObj = JSONObject()
        val massageObj = JSONObject()

        mSignaturePad = v.findViewById(R.id.massage_th_pad) as SignaturePad

        sSignaturePad = v.findViewById(R.id.massage_client_pad) as SignaturePad


        v.massage_his.setOnClickListener {
            val intent = Intent(context, HistoryActivity::class.java)
            intent.putExtra("his",4)
            startActivity(intent)
        }
        v.message_prefer.setOnClickListener {

            val popup = PopupMenu(context as Activity, message_prefer)

            popup.menuInflater.inflate(R.menu.message, popup.menu)

            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                if (item.itemId == R.id.Chocolate) {
                    jsonObj.put("massage_do_you_prefer", "Argan Relaxing Massage")
                    message_prefer_text.text = "Argan Relaxing Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.coffee) {
                    jsonObj.put("massage_do_you_prefer", "Aromatherapy Massage")
                    message_prefer_text.text = "Aromatherapy Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.gold) {
                    jsonObj.put("massage_do_you_prefer", "Ayurveda Massage")
                    message_prefer_text.text = "Ayurveda Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.lulur) {
                    jsonObj.put("massage_do_you_prefer", "Balinese Massage")
                    message_prefer_text.text = "Balinese Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.pearl) {
                    jsonObj.put("massage_do_you_prefer", "Gold Honey Massage")
                    message_prefer_text.text = "Gold Honey Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.traditional) {
                    jsonObj.put("massage_do_you_prefer", "Reflexology Massage")
                    message_prefer_text.text = "Reflexology Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.royal2) {
                    jsonObj.put("massage_do_you_prefer", "Pregnancy Massage")
                    message_prefer_text.text = "Pregnancy Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.royal) {
                    jsonObj.put("massage_do_you_prefer", "Pearl Honey Massage")
                    message_prefer_text.text = "Pearl Honey Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.pearl2) {
                    jsonObj.put("massage_do_you_prefer", "Mother's day Javanese Massage")
                    message_prefer_text.text = "Mother's day Javanese Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.salt) {
                    jsonObj.put("massage_do_you_prefer", "Head,Neck and Shoulder Massage 60 SR400.00")
                    message_prefer_text.text = "Head,Neck and Shoulder Massage 60 SR400.00"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.bridal) {
                    jsonObj.put("massage_do_you_prefer", "Herbal Massage")
                    message_prefer_text.text = "Herbal Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.Enzyme) {
                    jsonObj.put("massage_do_you_prefer", "Hot Stone Massage")
                    message_prefer_text.text = "Hot Stone Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.golden) {
                    jsonObj.put("massage_do_you_prefer", "Swedish Massage")
                    message_prefer_text.text = "Swedish Massage"
                    return@OnMenuItemClickListener true
                } else if (item.itemId == R.id.day) {
                    jsonObj.put("massage_do_you_prefer", "Mother's day Hotstone Massage")
                    message_prefer_text.text = "Mother's day Hotstone Massage"
                    return@OnMenuItemClickListener true
                }
                false
            })
            popup.show()
        }

        v.massageBtn.setOnClickListener {

            val pR: Int = radioMPregnant.checkedRadioButtonId
            val press: Int = radioPressure.checkedRadioButtonId

            when (pR) {
                R.id.PregnantMYes -> jsonObj.put("are_you_pregnant", 1)
            }
            when (press) {
                R.id.Firm -> jsonObj.put("how_do_you_prefer_your_massage_pressure", "Firm")
                R.id.Soft -> jsonObj.put("how_do_you_prefer_your_massage_pressure", "Soft")
                else -> {
                    jsonObj.put("how_do_you_prefer_your_massage_pressure", "Moderate")

                }


            }

            jsonObj.put("user_id", UserInfo.client_id)
            jsonObj.put("branch_id", UserInfo.branch_id)
            jsonObj.put("updated_by", UserInfo.user_id)
            jsonObj.put("created_by", UserInfo.user_id)

            if (addSvgSignatureToGallery(mSignaturePad!!.getSignatureSvg())) {
                jsonObj.put("tech_signature", mSignaturePad!!.getSignatureSvg())
            }
            if (addSvgSignatureToGallery(sSignaturePad!!.getSignatureSvg())) {
                jsonObj.put("client_signature", sSignaturePad!!.getSignatureSvg())
            }
            massageObj.put("client_id", UserInfo.client_id)

            if (UserInfo.client_id != 0) {
                val que = Volley.newRequestQueue(context)
                val req = JsonObjectRequest(Request.Method.POST, url2, massageObj,
                        Response.Listener { response ->
                            if (response.getString("success") != "0") {

                                val que = Volley.newRequestQueue(context)
                                val req = JsonObjectRequest(Request.Method.POST, url, jsonObj,
                                        Response.Listener { response ->
                                            if (response.getString("massage_do_you_prefer") != "") {
                                                Toast.makeText(context, getString(R.string.added), Toast.LENGTH_LONG).show()
                                                commitFrag(response.getInt("id"), "massage")
                                            } else {
                                                Toast.makeText(context, "fill all fields", Toast.LENGTH_LONG).show()
                                            }

                                        },
                                        Response.ErrorListener { error ->
                                            Log.e("TAG", "Massage error " + error.message)
                                            Toast.makeText(context, "fill all fields", Toast.LENGTH_LONG).show()
                                        })
                                req.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

                                que!!.add(req)
                            } else {
                                Toast.makeText(context, "fill customer health form first", Toast.LENGTH_LONG).show()

                            }
                        },
                        Response.ErrorListener { error ->
                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                        })
                req.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

                que!!.add(req)
            } else {
                Toast.makeText(context, getString(R.string.not_found_user), Toast.LENGTH_LONG).show()
            }

        }

        return v
    }

    private fun existUser(phone: String): Boolean {
        val url = Config.SITE_URL + "user/search"
        var exist: Boolean = false
        val jsonObj = JSONObject()
        jsonObj.put("phone", phone)
        val que = Volley.newRequestQueue(context)
        val req = JsonObjectRequest(Request.Method.POST, url, jsonObj,
                Response.Listener { response ->
                    if (response.getString("phone") != "") {
                        UserInfo.client_email = response.getString("email")
                        UserInfo.client_id = response.getInt("id")
                        UserInfo.client_name = response.getString("name")
                        UserInfo.client_phone = response.getString("phone")
                        exist = true
                    } else {
                        UserInfo.client_email = ""
                        UserInfo.client_id = 0
                        UserInfo.client_name = ""
                        UserInfo.client_phone = ""
                    }
                }, Response.ErrorListener { error ->
            Log.d("D", error.message.toString())
        })
        que.add(req)
        return exist
    }


    private fun commitFrag(id: Int, module: String) {
        val bundle = Bundle()
        val fragment = RatingFragment()
        bundle.putString("module", module)
        bundle.putInt("id", id)
        fragment.arguments = bundle
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
                //.addToBackStack(null)
                .replace(R.id.frameLayout, fragment)
                .commit()
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Cannot write images to external storage", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getAlbumStorageDir(albumName: String): File {
        // Get the directory for the user's public pictures directory.
        val file = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName)
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created")
        }
        return file
    }

    @Throws(IOException::class)
    fun saveBitmapToJPG(bitmap: Bitmap, photo: File) {
        val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBitmap)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        val stream = FileOutputStream(photo)
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        stream.close()
    }

    fun addJpgSignatureToGallery(signature: Bitmap): Boolean {
        var result = false
        try {
            val photo = File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()))
            saveBitmapToJPG(signature, photo)

            scanMediaFile(photo)
            result = true
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    private fun scanMediaFile(photo: File) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val contentUri = Uri.fromFile(photo)
        mediaScanIntent.data = contentUri
        context!!.sendBroadcast(mediaScanIntent)
    }

    fun addSvgSignatureToGallery(signatureSvg: String): Boolean {
        var result = false
        try {
            val svgFile = File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()))
            val stream = FileOutputStream(svgFile)
            val writer = OutputStreamWriter(stream)
            writer.write(signatureSvg)
            writer.close()
            stream.flush()
            stream.close()
            scanMediaFile(svgFile)
            result = true
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    /**
     * Checks if the app has permission to write to device storage
     *
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    fun verifyStoragePermissions(activity: Activity) {
        // Check if we have write permission
        val permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            )
        }
    }
}
=======
private val REQUEST_EXTERNAL_STORAGE = 1
private val PERMISSIONS_STORAGE = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
private var mSignaturePad: SignaturePad? = null
private var sSignaturePad: SignaturePad? = null
lateinit var txtPhoneLayout: TextInputLayout
lateinit var txtPhone: AppCompatEditText
override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    val v = inflater.inflate(R.layout.fragment_massage, container, false)
    txtPhoneLayout = v.findViewById(R.id.textPhoneLayout) as TextInputLayout
    txtPhone = v.findViewById(R.id.textPhone) as AppCompatEditText

    txtPhone.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
        if (txtPhone.text.toString().isEmpty()) {
            txtPhoneLayout.isErrorEnabled = true
            txtPhoneLayout.error = "Required Field!"
        } else {
            txtPhoneLayout.isErrorEnabled = false
            existUser(v.textPhone.text.toString())
        }
    }

    val nm: AppCompatEditText = v.findViewById(R.id.textName) as AppCompatEditText
    val ph: AppCompatEditText = v.findViewById(R.id.textPhone) as AppCompatEditText
    val em: AppCompatEditText = v.findViewById(R.id.textEmail) as AppCompatEditText

    nm.setText(UserInfo.client_name, TextView.BufferType.EDITABLE)
    ph.setText(UserInfo.client_phone, TextView.BufferType.EDITABLE)
    em.setText(UserInfo.client_email, TextView.BufferType.EDITABLE)

    nm.isEnabled = false
    ph.isEnabled = false
    em.isEnabled = false

    var url = Config.SITE_URL + "massages"
    val jsonObj = JSONObject()

    mSignaturePad = v.findViewById(R.id.massage_th_pad) as SignaturePad

    sSignaturePad = v.findViewById(R.id.massage_client_pad) as SignaturePad

    v.massageBtn.setOnClickListener {

        val pR: Int = radioMPregnant.checkedRadioButtonId
        val press: Int = radioPressure.checkedRadioButtonId

        when (pR) {
            R.id.PregnantMYes -> jsonObj.put("are_you_pregnant", 1)
        }
        when (press) {
            R.id.Firm -> jsonObj.put("how_do_you_prefer_your_massage_pressure", "Firm")
            R.id.Soft -> jsonObj.put("how_do_you_prefer_your_massage_pressure", "Soft")
            else -> {
                jsonObj.put("how_do_you_prefer_your_massage_pressure", "Moderate")

            }
        }

        jsonObj.put("massage_do_you_prefer", textType.text)
        jsonObj.put("user_id", UserInfo.client_id)
        jsonObj.put("branch_id", UserInfo.branch_id)
        jsonObj.put("updated_by", UserInfo.user_id)
        jsonObj.put("created_by", UserInfo.user_id)

        if (addSvgSignatureToGallery(mSignaturePad!!.getSignatureSvg())) {
            jsonObj.put("tech_signature", mSignaturePad!!.getSignatureSvg())
        }
        if (addSvgSignatureToGallery(sSignaturePad!!.getSignatureSvg())) {
            jsonObj.put("client_signature", sSignaturePad!!.getSignatureSvg())
        }
        if (UserInfo.client_id != 0) {
            val que = Volley.newRequestQueue(context)
            val req = JsonObjectRequest(Request.Method.POST, url, jsonObj,
                    Response.Listener { response ->
                        Toast.makeText(context, getString(R.string.added), Toast.LENGTH_LONG).show()
                        commitFrag(response.getInt("id"), "massage")
                    },
                    Response.ErrorListener { error ->
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                    })
            req.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

            que!!.add(req)
        } else {
            Toast.makeText(context, getString(R.string.not_found_user), Toast.LENGTH_LONG).show()
        }

    }

    return v
}

private fun existUser(phone: String): Boolean {
    val url = Config.SITE_URL + "user/search"
    var exist: Boolean = false
    val jsonObj = JSONObject()
    jsonObj.put("phone", phone)
    val que = Volley.newRequestQueue(context)
    val req = JsonObjectRequest(Request.Method.POST, url, jsonObj,
            Response.Listener { response ->
                if (response.getString("phone") != "") {
                    UserInfo.client_email = response.getString("email")
                    UserInfo.client_id = response.getInt("id")
                    UserInfo.client_name = response.getString("name")
                    UserInfo.client_phone = response.getString("phone")
                    exist = true
                } else {
                    UserInfo.client_email = ""
                    UserInfo.client_id = 0
                    UserInfo.client_name = ""
                    UserInfo.client_phone = ""
                }
            }, Response.ErrorListener { error ->
        Log.d("D", error.message.toString())
    })
    que.add(req)
    return exist
}


private fun commitFrag(id: Int, module: String) {
    val bundle = Bundle()
    val fragment = RatingFragment()
    bundle.putString("module", module)
    bundle.putInt("id", id)
    fragment.arguments = bundle
    (context as FragmentActivity).supportFragmentManager.beginTransaction()
            //.addToBackStack(null)
            .replace(R.id.frameLayout, fragment)
            .commit()
}


override fun onRequestPermissionsResult(requestCode: Int,
                                        permissions: Array<String>, grantResults: IntArray) {
    when (requestCode) {
        REQUEST_EXTERNAL_STORAGE -> {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.size <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Cannot write images to external storage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

fun getAlbumStorageDir(albumName: String): File {
    // Get the directory for the user's public pictures directory.
    val file = File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES), albumName)
    if (!file.mkdirs()) {
        Log.e("SignaturePad", "Directory not created")
    }
    return file
}

@Throws(IOException::class)
fun saveBitmapToJPG(bitmap: Bitmap, photo: File) {
    val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(newBitmap)
    canvas.drawColor(Color.WHITE)
    canvas.drawBitmap(bitmap, 0f, 0f, null)
    val stream = FileOutputStream(photo)
    newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
    stream.close()
}

fun addJpgSignatureToGallery(signature: Bitmap): Boolean {
    var result = false
    try {
        val photo = File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()))
        saveBitmapToJPG(signature, photo)

        scanMediaFile(photo)
        result = true
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return result
}

private fun scanMediaFile(photo: File) {
    val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
    val contentUri = Uri.fromFile(photo)
    mediaScanIntent.data = contentUri
    context!!.sendBroadcast(mediaScanIntent)
}

fun addSvgSignatureToGallery(signatureSvg: String): Boolean {
    var result = false
    try {
        val svgFile = File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()))
        val stream = FileOutputStream(svgFile)
        val writer = OutputStreamWriter(stream)
        writer.write(signatureSvg)
        writer.close()
        stream.flush()
        stream.close()
        scanMediaFile(svgFile)
        result = true
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return result
}

/**
 * Checks if the app has permission to write to device storage
 *
 *
 * If the app does not has permission then the user will be prompted to grant permissions
 *
 * @param activity the activity from which permissions are checked
 */
fun verifyStoragePermissions(activity: Activity) {
    // Check if we have write permission
    val permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    if (permission != PackageManager.PERMISSION_GRANTED) {
        // We don't have permission so prompt the user
        ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
        )
    }
}
}
>>>>>>> 0026944e737158b551053a24feabf29d5e80170d
