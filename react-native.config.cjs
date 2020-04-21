import { NativeModules } from 'react-native';

var RNBuildConfig = NativeModules.RNBuildConfig;

export default {
  getFlavor: function() {
    return RNBuildConfig.getFlavor();
  },
  getVersionName: function() {
    return RNBuildConfig.getVersionName();
  },
  getVersionCode: function() {
    return RNBuildConfig.getVersionCode();
  }
}
