{pkgs ? import <nixpkgs> {}}:
pkgs.mkShell {
  buildInputs = with pkgs; [
    graalvm11-ce
    maven
    idea.idea-ultimate # i have a license from uni
  ];
  shellHook = ''
  function run_dev {
    mvn compile quarkus:dev
  }
  '';
}
