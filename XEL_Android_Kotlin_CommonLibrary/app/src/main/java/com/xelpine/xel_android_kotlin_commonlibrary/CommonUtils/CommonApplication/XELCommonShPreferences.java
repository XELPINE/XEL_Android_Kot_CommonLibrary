package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * 프리퍼런스
 */
public class XELCommonShPreferences implements SharedPreferences {

	private SharedPreferences m_pref;
	private Editor m_editor;
	
	public XELCommonShPreferences(Context context){
		m_pref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
		m_editor = m_pref.edit();
	}
	
	public void putBoolean(String key, boolean value) {
		m_editor.putBoolean(key, value);
	}

	public void putFloat(String key, float value) {
		m_editor.putFloat(key, value);
	}


	public void putInt(String key, int value) {
		m_editor.putInt(key, value);
	}

	public void putLong(String key, long value) {
		m_editor.putLong(key, value);
	}
	
	public void putString(String key, String value) {
		m_editor.putString(key, value);
	}
	
	public void commit() {
		m_editor.commit();
	}

	public boolean getBoolean(String key, boolean value) {
		return m_pref.getBoolean(key, value);
	}

	@Override
	public float getFloat(String key, float value) {
		return m_pref.getFloat(key, value);
	}

	@Override
	public int getInt(String key, int value) {
		return m_pref.getInt(key, value);
	}

	@Override
	public long getLong(String key, long value) {
		return m_pref.getLong(key, value);
	}

	@Override
	public String getString(String key, String value) {
		return m_pref.getString(key, value);
	}
	
	public void remove(String key)
	{
		m_editor.remove(key);
	}

	@Override
	public boolean contains(String key) {
		return false;
	}

	@Override
	public Editor edit() {
		return m_editor;
	}

	@Override
	public Map<String, ?> getAll() {
		return null;
	}

	@Override
	public void registerOnSharedPreferenceChangeListener(
			OnSharedPreferenceChangeListener listener) {
	}

	@Override
	public void unregisterOnSharedPreferenceChangeListener(
			OnSharedPreferenceChangeListener listener) {
	}

    @Override
    public Set<String> getStringSet(String arg0, Set<String> arg1)
    {
        return null;
    }
}
